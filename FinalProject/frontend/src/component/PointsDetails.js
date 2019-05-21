import React, { Component } from 'react';
import axios from 'axios';
import Modal from 'react-responsive-modal'
import AdminNavbar from './AdminNavbar';
import {NavLink} from 'react-router-dom';
import { frontend, url } from '../config/config';
import {Redirect} from 'react-router';
import "../css/hackathonTable.css"
import Popup from "reactjs-popup";
import PieChart from 'react-minimal-pie-chart';
var swal = require('sweetalert')



class PointsDetails extends Component {
    constructor(props) {
        super(props);
        this.state = {  
            teams:[],
            hackathon:'',
            hackId:this.props.location.state.hackId,
            error_status:" "
        }
    }

    async componentDidMount() {
              const email=JSON.parse(localStorage.getItem('user'));
              await axios.get(url+`/hackathon/${this.state.hackId}`)
                .then((response, error) => {
                this.setState({
                    hackathon: response.data,
                    teams : response.data.teams,
                    error_status:" "
                })
                }).catch((error) => {
                    console.log("Error",error)
                    console.log("Error response",error.response.data)
                    this.setState({error_status:error.response.data})
                });

    }

    render() {   
        let redirectVar = null;
        if(!localStorage.getItem("user")){
            redirectVar = <Redirect to= "/login"/>
        }
        // let team = this.state.teams.map((row, key) => {
        //     var feesPaid = 0
        //     var users = row.users.map((user) => {
        //         return(
        //             <td value={user.id} name={user.name}>{user.name}</td>
        //         )
        //     })
        //     var paidUsers = row.paidUsers.map((user) => {
        //         if(user.organization != null) {
        //             if(this.state.hackathon.sponsors.some(item => user.organization.name === item.name)) {
        //                 console.log("in here disc")
        //                 feesPaid += this.state.hackathon.regFees-(this.state.hackathon.regFees*(this.state.hackathon.discount*0.01))
        //             }
        //         } 
        //         else 
        //             feesPaid += this.state.hackathon.regFees
        //         return(<div>
        //             <Popup
        //                 trigger={<td value={user.id} name={user.name}>{user.name}</td>}
        //                 position="top center"
        //                 on="hover"
        //                 >
        //                 <div className="card">
        //                     {user.email}
        //                 </div>
        //             </Popup>
        //             </div>
        //         )
        //     })
        //     return(    
                
        //         <tr>                    
        //             <td className="text-primary">{row.name}</td>
        //             <td className="text-primary">{row.paidCount}</td> 
        //             <td>
        //                 {users}
        //             </td>
        //             <td>
        //                 {paidUsers}
        //             </td>
        //             <td> $ {feesPaid}</td>
        //         </tr>
        //     )
        // })

        return ( 
        <div>
            {redirectVar}
            <AdminNavbar />
            <div>               
                <div class="card">
                <h1 class="center"> Points details of Hackathon {this.state.hackathon.name}</h1>            
                    <PieChart
                        data={[
                            { title: 'One', value: 10, color: '#E38627' },
                            { title: 'Two', value: 15, color: '#C13C37' },
                            { title: 'Three', value: 20, color: '#6A2135' },
                        ]}
                        style={{height: '100px'}}
                    />
                </div>
            </div>            
        </div> 
        );
    }
}
 
export default PointsDetails;
