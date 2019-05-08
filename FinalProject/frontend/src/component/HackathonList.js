import React, { Component } from 'react';
import {NavLink} from 'react-router-dom';
import UserNavbar from './UserNavbar';
import Modal from 'react-responsive-modal'
import axios from 'axios';
import "../css/hackathonTeam.css";
import {Redirect} from 'react-router';
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
            teams:[]
         }
    }

    componentDidMount(){
        axios.get(url+`/hackathonsByDate`)
        .then((response) => {
               this.setState({hackathonlist:response.data})
                console.log(response.data);
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
                this.setState({
                    owner:{
                        id:response.data.id,
                        name:response.data.name,
                        teams:response.data.participantTeam
                    },
                    teams:response.data.participantTeam
                })
                console.log(response.data);
        });

    }

    handleJoin = (index) => {
        //e.preventDefault();
        this.setState({ 
            openCreate: true,
            currentHackathonId: this.state.hackathonlist[index].id,
            currentHackathonMinTeamSize: this.state.hackathonlist[index].minTeamSize,
            currentHackathonMaxTeamSize: this.state.hackathonlist[index].maxTeamSize

        });
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
        //console.log("in change button",this.state.users.length, this.state.currentHackathonMaxTeamSize, this.state.currentHackathonMinTeamSize, this.state.lenMatch)
        const newShareholders = this.state.users.map((users, sidx) => {
            if (idx !== sidx) return users;
            return { ...users, id: evt.target.value };
    });

    this.setState({ 
        users: newShareholders,
        lenMatch : (this.state.users.length >= this.state.currentHackathonMinTeamSize) && (this.state.users.length <= this.state.currentHackathonMaxTeamSize)
        });
        //console.log("CHange check", this.state.users.length >= this.state.currentHackathonMinTeamSize, this.state.users.length <= this.state.currentHackathonMaxTeamSize)
    };


    handleSubmit = evt => {
        evt.preventDefault()
        const { name, users, owner } = this.state;
        console.log(users)
        //alert(`Incorporated: ${name} with ${users.length} shareholders`);
        const teamData=({
            name: name,
            users,
            hackathon: {id:this.state.currentHackathonId},
            owner
        })
        axios.post(url+'/team', teamData)
        .then((response)=>{
            console.log(response.data)
            this.props.history.push({
                pathname:'/payment',
                state: { hackId: this.state.currentHackathonId }
            })
        })

    };

    handleAddShareholder = () => {
        console.log("in Add button",this.state.users.length, this.state.currentHackathonMaxTeamSize, this.state.currentHackathonMinTeamSize, this.state.lenMatch)
        this.setState({
            users: this.state.users.concat([{ id: "" }]),
            lenMatch : (this.state.users.length-1 >= this.state.currentHackathonMinTeamSize) && (this.state.users.length-1 <= this.state.currentHackathonMaxTeamSize)
        });
        console.log("Add check", this.state.users.length >= this.state.currentHackathonMinTeamSize, this.state.users.length <= this.state.currentHackathonMaxTeamSize)
    };

    handleRemoveShareholder = idx => () => {
        console.log("in Close button",this.state.users.length, this.state.currentHackathonMaxTeamSize, this.state.currentHackathonMinTeamSize, this.state.lenMatch)
        this.setState({
            users: this.state.users.filter((s, sidx) => idx !== sidx),
            lenMatch : (this.state.users.length-1 >= this.state.currentHackathonMinTeamSize) && (this.state.users.length-1 <= this.state.currentHackathonMaxTeamSize)
        });
        console.log("Close check", this.state.users.length >= this.state.currentHackathonMinTeamSize, this.state.users.length <= this.state.currentHackathonMaxTeamSize)
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

    isTeamInHack = val => {
        return this.state.teams.some(item => val.teamId === item.teamId);
    }

    shouldJoin = (teams, key) => {
        var retVal = <button onClick={()=>this.handleJoin(key)} className="mb-4 ml-5 btn btn-submit bg-success text-white btn-lg ">Join Kartot</button>
        teams.map((team, key12) => {
            if (this.isTeamInHack(team)) {
                retVal = <div>
                    <button onClick={()=>this.handleCode(key)} className="mb-4 ml-5 btn btn-submit bg-success text-white btn-lg ">Code</button>
                    <button onClick={()=>this.handlePay(key)} className="mb-4 ml-5 btn btn-submit bg-success text-white btn-lg ">Pay</button>
                    </div>
                return retVal
            }
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
            {/* <button onClick={()=>this.handleJoin(key)} className="mb-4 ml-5 btn btn-submit bg-success text-white btn-lg ">Join</button> */}
            {this.shouldJoin(item.teams, key)}
        </div>
        );}

        var userList
        if(this.state.users!=null){
            userList=this.state.allUsers.map((u) => {
                if(u.role == 'hacker' && u.id != this.state.owner.id)
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
                        {/* <input
                        type="text"
                        placeholder={`Teammember #${idx + 1} name`}
                        value={shareholder.name}
                        onChange={this.handleShareholderNameChange(idx)}
                        /> */}
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
                    {/* disabled={this.state.currentHackathonMinTeamSize <= this.state.users.length <= this.state.currentHackathonMaxTeamSize } */}
                    <button disabled={!this.state.lenMatch}>Create</button>
                </form>
                        </Modal>
                    </div>
                </div> 
            );
    }
}
 
export default HackathonList;
