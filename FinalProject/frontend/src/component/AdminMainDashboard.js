import React, { Component } from 'react';
import Navbar from './Navbas';
import {Link} from 'react-router-dom';

class AdminMainDashboard extends Component {
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
                       <Link to="/create/hackathon" className=" btn btn-link text-white text"><h3>Create Hackathons</h3> </Link>
                 </ul>

                 <ul class="thumbnails bg-secondary m-5 p-5 col-sm-6 col-md-3">
                       <Link to="/admin/maindashboard" className="text-white text-center"><h3> View Hackathons </h3></Link>
                 </ul>

                 <ul class="thumbnails bg-secondary m-5 p-5 col-sm-6 col-md-3">
                  <Link to="/profile" className="text-white"> <h3> Profile </h3></Link>
                 </ul>
            
                  </div>
                </div>  

            </div> );
    }
}
 
export default AdminMainDashboard;