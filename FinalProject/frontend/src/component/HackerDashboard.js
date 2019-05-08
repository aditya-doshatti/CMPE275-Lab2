import React, { Component } from 'react';
import Navbar from './Navbas';
import {Link} from 'react-router-dom';
import axios from 'axios';
import UserNavbar from './UserNavbar';
import {Redirect} from 'react-router';
import { frontend, url } from '../config/config';

class HackerDashboard extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            isOwner:false
         }
    }

    componentWillMount(){
        console.log("localStorage",localStorage.getItem('user'))
        const email=JSON.parse(localStorage.getItem('user'));
        axios.get(url+`/user/profile/${email}`)
        .then((response) => {
                this.setState({
                    isOwner:response.data.owner
                })
                console.log(response.data);
        });
    }
    render() { 
        let redirectVar = null;
        if(!localStorage.getItem("user")){
            redirectVar = <Redirect to= "/login"/>
        }
        var isOwnerSection
         if (this.state.isOwner) {
         isOwnerSection = <ul class="thumbnails bg-secondary m-5 p-5 col-sm-6 col-md-3">
                  <Link to="/approveRequests" className="text-white"> <h3> Pending Approvals </h3></Link>
                 </ul>
         }

        return ( 
            <div>
                 {redirectVar}
               <UserNavbar />
                <div>
                  <div class = "row">  
                  <ul class="thumbnails bg-secondary m-5 p-5 col-sm-6 col-md-3">
                       <Link to="/view/hackathon" className=" btn btn-link text-white text"><h3>View Hackathons</h3> </Link>
                 </ul>

                 <ul class="thumbnails bg-secondary m-5 p-5 col-sm-6 col-md-3">
                       <Link to="/profile" className="text-white text-center"><h3> View Profile </h3></Link>
                 </ul>

                 {isOwnerSection}
            
                  </div>
                </div>  

            </div> );
    }
}
 
export default HackerDashboard;