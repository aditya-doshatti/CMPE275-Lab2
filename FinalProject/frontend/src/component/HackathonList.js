import React, { Component } from 'react';
import {NavLink} from 'react-router-dom';
import Navbar from './Navbas';
import axios from 'axios';
var swal = require('sweetalert')
const url="http://localhost:8080"

class HackathonList extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            hackathonlist:[],
            error_message:" "
         }
    }

    componentDidMount(){
        axios.get(url+`/hackathonsByDate`)
        .then((response) => {
               this.setState({hackathonlist:response.data})
                console.log(response.data);
        });

    }

    render() { 

        return ( <div>

            <h1>Hello there</h1>
        </div> );
    }
}
 
export default HackathonList;
