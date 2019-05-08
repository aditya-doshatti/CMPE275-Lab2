import React, { Component } from 'react';
import UserNavbar from './UserNavbar';
import {Redirect} from 'react-router';
import axios from 'axios';
import { frontend, url } from '../config/config';
import "../css/card.css"

var swal = require('sweetalert')

class Hackathon extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            hackId:this.props.location.state.hackId,
            userId:'',
            userOrg:'',
            userTeams:[],
            currentTeamId:'',
            currUsers:[],
            hackathon:"",
            submissionLink:''
         }
        this.handleEvent=this.handleEvent.bind(this);
    }

    async componentWillMount(){
        console.log("localStorage",localStorage.getItem('user'))
        const email=JSON.parse(localStorage.getItem('user'));
        axios.get(url+`/user/profile/${email}`)
        .then((response) => {
                this.setState({
                    userId:response.data.id,
                })
                if (response.data.participantTeam!=null) {
                    this.setState({
                        userTeams:response.data.participantTeam
                    })
                }
                console.log(response.data);
        });
        await axios.get(url+`/hackathon/${this.state.hackId}`)
        .then((response) =>{
            console.log("here inside axios",response.data)
            this.setState({
                hackathon: response.data,
            })
        }
        );
        this.state.userTeams.map((team,key) => {
            if (team!=null) {
                if (team.hackathon.id === this.state.hackId) {
                    this.setState({
                        currentTeamId:team.teamId,
                    })
                }
            }
        })
        axios.get(url+`/team/${this.state.currentTeamId}`)
        .then((response) =>{
            console.log("here inside axios",response.data)
            this.setState({
                currUsers: response.data.users,
                submissionLink:response.data.submissionLink
            })
        }
        );
        console.log(this.state)
    }

    // currentTeamId = () => {
    //     this.userTeams.map((team,key) => {
    //         if (team.hackathon.id === this.state.hackId) {
    //             this.setState({
    //                 currentTeamId:team.teamId
    //             })
    //         }
    //     })

    // }

    onUploadClick(e) {
        let files = e.target.files;
        console.log("Data files", files);
    }

    handleEvent(e){
        let target=e.target
        let name=target.name;
        this.setState({
            [name]:target.value    
        });
        console.log("handle Ecent for update profile")
    }

    onSubmit = (e) => {
        const teamData=({
            teamId: this.state.currentTeamId,
            submissionLink: this.state.submissionLink
        })
        axios.post(url+`/team/${this.state.currentTeamId}/submit`, teamData)
        .then((response)=>{
            console.log(response.data)
            this.props.history.push({
                pathname:'/dashboard'
            })
        })
        swal("Submission Success","You will get results soon","success")
        
    }
    render() { 
        let redirectVar = null;
        if(!localStorage.getItem("user")){
            redirectVar = <Redirect to= "/login"/>
        }
        var Authors = this.state.currUsers.map((item, key) => <div>
                <td><span>{item.name}</span></td>
            </div>)
        console.log("aditya daitya",this.state.submissionLink)
        return ( 
            <div>
                 {redirectVar}
                <UserNavbar />
                <div class="row">
                    
                    <div class="col-md-9">
                        <div class="card" >
                            <div class="card-body">
                                <h5 class="card-title"><b>Problem Description for {this.state.hackathon.name}</b></h5>
                                <h6 class="card-subtitle mb-2 text-muted">2 Sum Problem</h6>
                                <p class="card-text">Given an array of integers, return indices of the two numbers such that they add up to a specific target.</p>
                                <p class="card-text">You may assume that each input would have exactly one solution, and you may not use the same element twice.</p>
                                <hr></hr>
                                <samp>
                                    <h6><b>Example</b></h6>
                                    <h6>Input</h6>
                                    nums = [2, 7, 11, 15], target = 9 <br></br><br></br>
                                    <h6>Output</h6>
                                    [0, 1]

                                </samp>
                                <hr></hr>
                                {/* <a href="#" class="card-link">Upload Solution</a> */}
                                {/* <input type="file" name="file"  onChange={(e)=>this.onUploadClick(e)}></input> */}
                                
                                    <div class="form-group row">
                                    <input type="text" name="submissionLink" class="form-control col-md-8" id="githublinkinput" onChange={this.handleEvent} placeholder="github link for submission"></input>
                                    <button onClick={this.onSubmit} class="btn btn-primary col-md-4"  id="submitbutton">Submit</button>
                                    </div>
                                        
                               
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">      
                        <div class="table-container">
                            <table class="table table-dark">                        
                            <tbody>
                                <tr>                            
                                <td>Author</td>
                                <td>{Authors}</td>                                
                                </tr>
                                <tr>                                
                                <td>Difficulty</td>
                                <td>Easy</td>                                
                                </tr>
                                <tr>                                
                                <td>Max Points</td>
                                <td>50</td>                                
                                </tr>
                            </tbody>
                            </table>
                        </div>            
                    </div>
                </div>
            </div>
         );
    }
}
 
export default Hackathon;