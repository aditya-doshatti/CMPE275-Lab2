import React, { Component } from 'react';
import {NavLink} from 'react-router-dom';
import Navbar from './Navbas';
import Modal from 'react-responsive-modal'
import axios from 'axios';
import "../css/hackathonTeam.css";

var swal = require('sweetalert')

const url="http://localhost:8080"

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
            allUsers:[]
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

    }

    handleJoin = (id) => {
        //e.preventDefault();
        this.setState({ 
            openCreate: true,
            currentHackathonId: id
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
    const newShareholders = this.state.users.map((users, sidx) => {
        if (idx !== sidx) return users;
        return { ...users, id: evt.target.value };
    });

    this.setState({ users: newShareholders });
    };

    handleSubmit = evt => {
        evt.preventDefault()
        const { name, users } = this.state;
        console.log(users)
        alert(`Incorporated: ${name} with ${users.length} shareholders`);
        const teamData=({
            name: name,
            users,
            hackathon: {id:this.state.currentHackathonId}
        })
        axios.post(url+'/team', teamData)
        .then((response)=>{
            console.log(response.data)
        })
    };

    handleAddShareholder = () => {
        this.setState({
            users: this.state.users.concat([{ id: "" }])
        });
    };

    handleRemoveShareholder = idx => () => {
        this.setState({
            users: this.state.users.filter((s, sidx) => idx !== sidx)
        });
    };

    render() { 

        var items
        if(this.state.hackathonlist!=null) {
        items = this.state.hackathonlist.map((item, key) => <div className="row text-center mt-4 ml-5">
            <span className="mt-2 ml-5 text-info pull-right font-weight-bold btn-lg">{item.name}</span>
            <button onClick={()=>this.handleJoin(item.id)} className="mb-4 ml-5 btn btn-submit bg-success text-white btn-lg ">Join</button>
        </div>
        );}

        var userList
        if(this.state.users!=null){
            userList=this.state.allUsers.map((u) => {
                return(
                    <option value={u.id}>{u.name} : ({u.email})</option>
                )
            });}
        return ( <div> 
                    <div className="container-fluid">
                    <div className=" col-lg-7 mb-5  mt-5 ml-5 bg-white border border-light">
                    <h1 class="ml-9">Join Hackathon</h1>
                            {items}
                    </div>
                    <Modal open={this.state.openCreate} onClose={this.onCloseCreateModal} focusTrapped>
                    <form onSubmit={this.handleSubmit}>
                    <input
                    type="text"
                    placeholder="Team name, e.g. Magic Everywhere LLC"
                    value={this.state.name}
                    onChange={this.handleNameChange}
                    />

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
                        -
                        </button>
                    </div>
                    ))}
                    <button
                    type="button"
                    onClick={this.handleAddShareholder}
                    className="small"
                    >
                    Add Shareholder
                    </button>
                    <button>Incorporate</button>
                </form>
                        </Modal>
                    </div>
                </div> 
            );
    }
}
 
export default HackathonList;
