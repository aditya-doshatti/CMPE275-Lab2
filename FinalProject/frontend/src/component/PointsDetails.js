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
import ReactSvgPieChart from "react-svg-piechart"
var swal = require('sweetalert')

class PointsDetails extends Component {
    constructor(props) {
        super(props);
        this.state = {  
            teams:[],
            hackathon:'',
            hackId:this.props.location.state.hackId,
            error_status:" ",
            currentTeamDetails:[],
            totalAmountPaid:0,
            totalAmountUnpaid:0,
            totalSponsors:0,
            totalExpenses:0,
            totalProfit:0
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
                this.state.teams.map((row, key) => {
                    this.getTeamPaymentDeatails(row.teamId)
                })
                this.setChartData()
                }).catch((error) => {
                    console.log("Error",error)
                    console.log("Error response",error.response.data)
                    this.setState({error_status:error.response.data})
                });

    }
     
    getTeamPaymentDeatails = val =>{
        axios.get(url+`/payments/${val}`)
        .then((response, error) => {
            console.log("Checking", response.data)
            this.setState({
                currentTeamDetails: this.state.currentTeamDetails.concat(response.data)
            })
        })
        //console.log(this.state)
    }

    setChartData = () => {
        var totalAmountPaid = 0, totalAmountUnpaid = 0, totalSponsors = 0, totalExpenses = 0, totalProfit = 0
        this.state.currentTeamDetails.map((detail) => {
            totalAmountPaid += detail.amount
        })
        this.state.hackathon.teams.map((team) => {
            totalAmountUnpaid += ((team.users.length - team.paidUsers.length)*this.state.hackathon.regFees)
        })
        totalSponsors = this.state.hackathon.sponsors.length * 1000
        totalExpenses = 100
        totalProfit = totalAmountPaid + totalSponsors - totalExpenses
        this.setState({
            totalAmountPaid:totalAmountPaid,
            totalAmountUnpaid:totalAmountUnpaid,
            totalExpenses:totalExpenses,
            totalProfit:totalProfit,
            totalSponsors:totalSponsors
        })
        console.log("inside set chart", this.state)
    }



    render() {   
        let redirectVar = null;
        if(!localStorage.getItem("user")){
            redirectVar = <Redirect to= "/login"/>
        }
        var tot = 0
        this.state.currentTeamDetails.map((detail) => {
            tot += detail.amount
        })
        return ( 
        <div>
            {redirectVar}
            <AdminNavbar />
            <div>               
                <div class="card">
                <h1 class="center"> Points details of Hackathon {this.state.hackathon.name}</h1>     
                <div class="container" style={{height:300}}>
                <ReactSvgPieChart
                data={[
                    { title: 'totalAmountPaid', value: tot, color: '#138620' },
                    { title: 'totalAmountUnpaid', value: this.state.totalAmountUnpaid, color: '#C13C37' },
                    { title: 'totalSponsors', value: this.state.totalSponsors, color: '#6A2135' },
                    { title: 'totalExpenses', value: this.state.totalExpenses, color: '#2A0135' },
                    { title: 'totalProfit', value: this.state.totalProfit, color: '#E38627' },
                ]}
                viewBoxSize={20}
                strokeWidth={0}
                expandSize={1}
                // If you need expand on hover (or touch) effect
                expandOnHover
                /> </div>  
            </div> 
            </div>
        </div> 
        );
    }
}
 
export default PointsDetails;
