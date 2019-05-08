import React, { Component } from 'react';
import axios from 'axios';
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
            error_status:" ",
        }
    }

    async componentDidMount() {
      
            await axios.get('http://localhost:8080/hackathons')
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

    render() {   
        
        let listdetails = this.state.listed.map((row) => {
            return(                
                
                <tr>
                    <td className="text-primary">{row.id}</td>                     
                    <td className="text-primary">{row.name}</td>
                    <td className="text-primary">{row.startDate}</td>
                    <td className="text-primary">{row.endDate}</td>
                    <td className="text-primary">{row.regFees}</td>           
                   
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
                
                    <table class="table mt-4 bg w-100 text-center border rounded shadow-lg">
                        <thead>
                            <tr>
                                <th><em>Id</em></th>
                                <th><em>Hackathon Name</em></th>
                                <th><em>Start Date</em></th>
                                <th><em>End Date</em></th>
                                <th><em>Registration Fees</em></th>
                            </tr>  
                        </thead>
                        <tbody>
                            {listdetails}                        
                        </tbody>
                    </table>
                </div>               
            </div>            
        </div> 
        );
    }
}
 
export default AdminDashboard;
