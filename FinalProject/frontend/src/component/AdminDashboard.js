import React, { Component } from 'react';
import axios from 'axios';
import Modal from 'react-responsive-modal'
import {NavLink} from 'react-router-dom';

import "../css/hackathonTable.css"

const url="http://localhost:8080"

const styles = theme => ({
    root: {
      width: '100%',
      marginTop: theme.spacing.unit * 3,
      overflowX: 'auto',
    },
    table: {
      minWidth: 700,
    },
});

class AdminDashboard extends Component {
    constructor(props) {
        super(props);
        this.state = {  
            listed:[],
            adminId:0,
            error_status:" ",
            openCreate: false,
            openLeave: false,
            openJoin: false,
            openSponsor:true
        }
    }

    async componentDidMount() {
              const email=JSON.parse(localStorage.getItem('user'));
              await axios.get(url+`/user/profile/${email}`)
                .then((response) => {
                        this.setState({
                            adminId:response.data.id,
                        })
                        console.log(response.data);
                })

                var adminId=this.state.adminId;
             axios.get(url+`/hackathons/${adminId}`)
            .then((response, error) => {
                this.setState({
                    listed : this.state.listed.concat(response.data),
                    error_status:" "
                })
                console.log("listed"+this.state.listed)
            }).catch((error) => {
                console.log("Error",error)
                console.log("Error response",error.response.data)
                this.setState({error_status:error.response.data})
            });

    }

    onOpenJoinModal = (e) => {
        e.preventDefault();
        this.setState({ openJoin: true });
      };

      onOpenSponsorModal = (e) => {
        e.preventDefault();
        this.setState({ openSponsor: true });
      };

      onCloseSponsorModal = (e) => {
        e.preventDefault();
        this.setState({ openSponsor: false });
    };

      onCloseCreateModal = (e) => {
        e.preventDefault();
        this.setState({ openJoin: false });
    };


    render() {   
        var a,b
        let listdetails = this.state.listed.map((row) => {
           a=row.judges.map(detail=>{return(<h5 className="text-background">{detail.name} <span className="text-muted">  Screen Name:</span>{detail.screenName}</h5>)})
           b=row.sponsors.map(detail=>{return(<h5 className="text-background">{detail.name}</h5>)})


            return(                
                
                <tr>                    
                    <td className="text-primary">{row.name}</td>
                    <td className="text-primary">({row.startDate}) - ({row.endDate})</td>
                    <td className="text-primary">{row.regFees}/{row.discount}</td>
                    <td className="text-primary">{row.minTeamSize}/{row.maxTeamSize}</td> 
                    <td>
                        <button className="btn btn-info" onClick={this.onOpenJoinModal}>Judges</button>
                        <button className="btn btn-info ml-2"  onClick={this.onOpenSponsorModal}>Organizers</button>
                        <button className="btn btn-info ml-2">Teams</button>
                    </td> 
                    <td className="text-primary">
                        <button className="btn btn-secondary">Close</button>
                        <button className="btn btn-secondary ml-2">Finalize</button>
                    </td>              
                </tr>
            )
        })

        return ( 
        <div>
            <div>               
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="#">
                    <img src={require('../images/2.png')} class="d-inline-block align-top" alt=""></img>
                    
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>      
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>             
                    </ul>
                    <form class="form-inline my-2 my-lg-0">
                    
                    </form>
                </div>
                </nav>
                <div class="card">
                
                    <table class="table mt-4 bg w-100 border rounded shadow-lg">
                        <thead>
                            <tr>
                                <th><em>Hackathon Name</em></th>
                                <th><em>Start Date /<br></br> End Date</em></th>
                                <th><em>Registration <br></br>Fees / Discount(%)</em></th>
                                <th><em>(Min/Max)<br></br>Team Member</em></th>
                                <th><em>View</em></th>
                                <th><em>Update</em></th>
                            </tr>  
                        </thead>
                        <tbody>
                            {listdetails}                        
                        </tbody>
                    </table>
                </div> 

                <Modal  className="w-100" open={this.state.openJoin} onClose={this.onCloseCreateModal} focusTrapped>
                <div className="w-100">
                <h4  className="text-info">View Judges:</h4><hr></hr>
                     {a}
                </div>
                </Modal>

                <Modal className="w-75" open={this.state.openSponsor} onClose={this.onCloseSponsorModal} focusTrapped>
                <div className="w-25" >
                <h4 className="text-info">View Organizations:</h4><hr></hr>
                     {b}
                </div>
                </Modal>

            </div>            
        </div> 
        );
    }
}
 
export default AdminDashboard;