import React, { Component } from 'react';
import Navbar from './Navbas';
import {Link} from 'react-router-dom';

class HackerDashboard extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 

        return ( 
            <div>
                <Navbar />
                <div>
                  <div class = "row">  
                  <ul class="thumbnails bg-secondary m-5 p-5 col-sm-6 col-md-3">
                       <Link to="/" className=" btn btn-link text-white text"><h3>View Hackathons</h3> </Link>
                 </ul>

                 <ul class="thumbnails bg-secondary m-5 p-5 col-sm-6 col-md-3">
                       <Link to="/profile" className="text-white text-center"><h3> View Profile </h3></Link>
                 </ul>

                 <ul class="thumbnails bg-secondary m-5 p-5 col-sm-6 col-md-3">
                  <Link to="/" className="text-white"> <h3> Pending Approvals </h3></Link>
                 </ul>
            
                  </div>
                </div>  

            </div> );
    }
}
 
export default HackerDashboard;