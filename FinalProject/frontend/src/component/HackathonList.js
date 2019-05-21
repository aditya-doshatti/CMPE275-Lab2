import React, { Component } from 'react';
import {NavLink} from 'react-router-dom';
import UserNavbar from './UserNavbar';
import Modal from 'react-responsive-modal'
import axios from 'axios';
import "../css/hackathonTeam.css";
import {Redirect} from 'react-router';
import Popup from "reactjs-popup";
import { frontend, url } from '../config/config';

var swal = require('sweetalert')

class HackathonList extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            name: "",
            users: [],
            hackathonlist:[],
            error_message:"",
            openCreate:false,
            currentHackathonId:0,
            allUsers:[],
            owner:{id:0, name:""},
            lenMatch:false,
            teams:[],
            judgesHacks:[],
            currentHackathonMinTeamSize:0,
            currentHackathonMaxTeamSize:0,
            checkValid:true
         }
    }

    componentDidMount(){
        axios.get(url+`/hackathonsByDate`)
        .then((response) => {
               this.setState({hackathonlist:response.data})
        });
        axios.get(url+`/users`)
        .then((response) => {
            this.setState({
                allUsers:response.data
            })
        });
        const email=JSON.parse(localStorage.getItem('user'));
        axios.get(url+`/user/profile/${email}`)
        .then((response) => {
                var lists = response.data.ownsTeams.concat(response.data.participantTeam)
                this.setState({
                    owner:{
                        id:response.data.id,
                        name:response.data.name,
                        teams:lists
                    },
                    teams:lists,
                    judgesHacks:response.data.judgesHackathons
                })
        });

    }

    handleJoin = (index) => {
        this.setState({ 
            openCreate: true,
            currentHackathonId: this.state.hackathonlist[index].id,
            currentHackathonMinTeamSize: this.state.hackathonlist[index].minTeamSize,
            currentHackathonMaxTeamSize: this.state.hackathonlist[index].maxTeamSize,
        });
        this.setState({
            lenMatch : (this.state.users.length+1 >= this.state.hackathonlist[index].minTeamSize) && (this.state.users.length+1 <= this.state.hackathonlist[index].maxTeamSize)
        })
    }

    onCloseCreateModal = (e) => {
        e.preventDefault()
        this.setState({ 
            openCreate: false,
            currentHackathonId: 0
        });
    }

    handleNameChange = evt => {
        this.setState({ name: evt.target.value });
      };
    
    handleShareholderNameChange = idx => evt => {
        const newShareholders = this.state.users.map((users, sidx) => {
            if (idx !== sidx) return users;
            return { ...users, id: evt.target.value };
    });

        this.setState({ 
        users: newShareholders,
        lenMatch : (this.state.users.length +1 >= this.state.currentHackathonMinTeamSize) && (this.state.users.length +1 <= this.state.currentHackathonMaxTeamSize)
        }, function () {
            console.log(this.state.users)
            this.checkValid(this.state.users);
            console.log(this.state.checkValid)
        });
        console.log(this.state.users, newShareholders)
    };


    handleSubmit = evt => {
        evt.preventDefault()
        const { name, users, owner } = this.state;
        var ownerObj = {"id":this.state.owner.id}
        users.push(ownerObj)
        const teamData=({
            name: name,
            users,
            hackathon: {id:this.state.currentHackathonId},
            owner
        })
        axios.post(url+'/team', teamData)
        .then((response)=>{
            this.props.history.push({
                pathname:'/payment',
                state: { hackId: this.state.currentHackathonId }
            })
        })

    };

    handleAddShareholder = () => {
        this.setState({
            users: this.state.users.concat([{ id: "" }]),
            lenMatch : (this.state.users.length+1 >= this.state.currentHackathonMinTeamSize) && (this.state.users.length+1 <= this.state.currentHackathonMaxTeamSize)
        });
    };

    handleRemoveShareholder = idx => () => {
        this.setState({
            users: this.state.users.filter((s, sidx) => idx !== sidx),
            lenMatch : (this.state.users.length+1 >= this.state.currentHackathonMinTeamSize) && (this.state.users.length+1 <= this.state.currentHackathonMaxTeamSize)
        }, function () {
            console.log(this.state.users)
            this.checkValid(this.state.users);
            console.log(this.state.checkValid)
        });
    };

    handleCode = key => {
        this.props.history.push({
                pathname:'/hackathon',
                state: { 
                    hackId: this.state.hackathonlist[key].id
                }
        })
    }

    handlePay = key => {
        this.props.history.push({
                pathname:'/payment',
                state: { 
                    hackId: this.state.hackathonlist[key].id
                }
        })
    }

    handleJudge = key => {
        this.props.history.push({
                pathname:'/judge/hackathon',
                state: { 
                    hackId: this.state.hackathonlist[key].id
                }
        })
    }

    isTeamInHack = val => {
        return this.state.teams.some(item => val.teamId === item.teamId);
    }

    isJudgeThisHack = val => {
        return this.state.judgesHacks.some(item => val.id === item.id)
    }

    isPaymentDone = val => {
        return val.paidUsers.some(item => this.state.owner.id === item.id)
    }

    isAllPaymentDone = val => {
        return val.paidUsers.length === val.users.length
    }

    shouldJoin = (teams, key) => {
        var retVal, judge
        if (this.isJudgeThisHack(this.state.hackathonlist[key])) {
            if(this.state.hackathonlist[key].open){
                retVal = <Popup
                trigger={<button disabled={this.state.hackathonlist[key].open} onClick={()=>this.handleJudge(key)} 
                className="mb-4 ml-5 btn btn-submit bg-success text-white btn-lg ">Judge</button>}
                position="top center"
                on="hover"
                >
                <div className="card">
                    <span> Hackthon must be closed and not finalized to be able to judge. Contact Admin</span>
                </div>
                </Popup>
            }
            else {
                retVal = <button disabled={this.state.hackathonlist[key].open} onClick={()=>this.handleJudge(key)} 
                className="mb-4 ml-5 btn btn-submit bg-success text-white btn-lg ">Judge</button>
            }            
            return retVal
        }
        else {
            retVal = <div>
                <button onClick={()=>this.handleJoin(key)} className="mb-4 ml-5 btn btn-submit bg-success text-white btn-lg ">Join</button>
                <span className="mt-2 ml-5 text-info pull-right font-weight-bold btn-lg">{this.state.hackathonlist[key].startDate}</span>
                <span className="mt-2 ml-5 text-info pull-right font-weight-bold btn-lg">{this.state.hackathonlist[key].endDate}</span>
                <span className="mt-2 ml-5 text-info pull-right font-weight-bold btn-lg">${this.state.hackathonlist[key].regFees}</span>
            </div>
            var join, pay
            teams.map((team, key12) => {
                if (this.isTeamInHack(team)) {
                    if(!this.isAllPaymentDone(team) || !this.state.hackathonlist[key].open){
                        join = <Popup
                        trigger={<button disabled={!this.isAllPaymentDone(team) || !this.state.hackathonlist[key].open} onClick={()=>this.handleCode(key)} 
                                className="mb-4 ml-5 btn btn-submit bg-success text-white btn-lg ">Code</button>}
                        position="top center"
                        on="hover"
                        >
                        <div className="card">
                            <span> All users must have paid the registration fees and Hackathon must be open for submission</span>
                        </div>
                        </Popup>
                    }
                    else {
                        join = <button disabled={!this.isAllPaymentDone(team) || !this.state.hackathonlist[key].open} onClick={()=>this.handleCode(key)} 
                                className="mb-4 ml-5 btn btn-submit bg-success text-white btn-lg ">Code</button>
                    }
                    if (this.isPaymentDone(team)) {
                        pay = <Popup
                        trigger={<button disabled={this.isPaymentDone(team)} onClick={()=>this.handlePay(key)} 
                                    className="mb-4 ml-5 btn btn-submit bg-success text-white btn-lg ">Pay</button>}
                        position="top center"
                        on="hover"
                        >
                        <div className="card">
                            <span> Payment already done for this hackathon</span>
                        </div>
                        </Popup>
                    }
                    else {
                        pay = <button disabled={this.isPaymentDone(team)} onClick={()=>this.handlePay(key)} 
                        className="mb-4 ml-5 btn btn-submit bg-success text-white btn-lg ">Pay</button>
                    }
                    retVal = <div>
                        {join}
                        {pay}
                        {/* <button disabled={!this.isAllPaymentDone(team) || !this.state.hackathonlist[key].open} onClick={()=>this.handleCode(key)} className="mb-4 ml-5 btn btn-submit bg-success text-white btn-lg ">Code</button>
                        <button disabled={this.isPaymentDone(team)} onClick={()=>this.handlePay(key)} className="mb-4 ml-5 btn btn-submit bg-success text-white btn-lg ">Pay</button> */}
                        </div>
                    return retVal
                }
            })
            return retVal
        }
    }

    checkValid = (users) => {
        const uniqueTags = []
        var retVal = false
        if(users != null) {
            users.map((user) => {
                if (!uniqueTags.some(item => parseInt(user.id) === parseInt(item.id))) {
                    uniqueTags.push(user)
                }
            })
            if(users.length === uniqueTags.length && this.state.lenMatch)
                retVal = true
            else
                retVal = false
        }
        else {
            retVal = this.state.lenMatch
        }
        this.setState({
            checkValid : retVal
        }, function () {
            this.setState({
                checkValid : this.state.checkValid
            })
        })
        return retVal
    }

    render() {
        let redirectVar = null;
        if(!localStorage.getItem("user")){
            redirectVar = <Redirect to= "/login"/>
        }

        var items
        var shoulJoin
        if(this.state.hackathonlist!=null) {
        items = this.state.hackathonlist.map((item, key) => <div className="row text-center mt-4 ml-5">
            <span className="mt-2 ml-5 text-info pull-right font-weight-bold btn-lg">{item.name}</span>
            {this.shouldJoin(item.teams, key)}
        </div>
        );}

        var userList
        if(this.state.users!=null){
            userList=this.state.allUsers.map((u) => {
                if(u.role == 'hacker' && u.id != this.state.owner.id && !u.judgesHackathons.some(item => this.state.currentHackathonId === item.id) 
                    && !u.participantTeam.some(item => this.state.currentHackathonId === item.hackathon.id))
                    return(
                        <option value={u.id}>{u.name} : ({u.email})</option>
                    )
            });}
        return ( <div> 
                  {redirectVar}
                  <UserNavbar />
                    <div className="container-fluid">
                    <div className=" col-lg-7 mb-5  mt-5 ml-5 bg-white border border-light">
                    <h1 class="ml-9">Join Hackathon</h1>
                            {items}
                    </div>
                    <Modal open={this.state.openCreate} onClose={this.onCloseCreateModal} focusTrapped>
                    <form onSubmit={this.handleSubmit}>
                    <input
                    required={true}
                    type="text"
                    placeholder="Team name, e.g. Magic Everywhere LLC"
                    value={this.state.name}
                    onChange={this.handleNameChange}
                    />
                    <div>
                        <h3>Team Owner</h3>
                        {this.state.owner.name}
                    </div>

                    <h4>Team Members</h4>

                    {this.state.users.map((shareholder, idx) => (
                    <div className="shareholder">
                        <select id="addTeamMember" name="addTeamMember" className="w-50 btn-md"  onChange={this.handleShareholderNameChange(idx)}>
                        <option default>--default--</option>
                            {userList}
                        </select>
                        <button
                        type="button"
                        onClick={this.handleRemoveShareholder(idx)}
                        className="small"
                        >
                        X
                        </button>
                    </div>
                    ))}
                    <button
                    type="button"
                    onClick={this.handleAddShareholder}
                    className="small"
                    >
                    Add Teammember
                    </button>
                    <Popup
                        trigger={<button disabled={!this.state.checkValid}>Create</button>}
                        position="top center"
                        on="hover"
                        >
                        <div className="card">
                            <span> All users must be unique and must match team size requirements</span>
                        </div>
                    </Popup>
                </form>
                        </Modal>
                    </div>
                </div> 
            );
    }
}
 
export default HackathonList;
