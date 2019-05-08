// import React, { Component } from 'react';
// import axios from 'axios';
// import {NavLink} from 'react-router-dom';

// import PropTypes from 'prop-types';
// import { withStyles } from '@material-ui/core/styles';
// import Table from '@material-ui/core/Table';
// import TableBody from '@material-ui/core/TableBody';
// import TableCell from '@material-ui/core/TableCell';
// import TableHead from '@material-ui/core/TableHead';
// import TableRow from '@material-ui/core/TableRow';
// import Paper from '@material-ui/core/Paper';

// const url="http://localhost:8080"

// const styles = theme => ({
//     root: {
//       width: '100%',
//       marginTop: theme.spacing.unit * 3,
//       overflowX: 'auto',
//     },
//     table: {
//       minWidth: 700,
//     },
// });

// class AdminDashboard extends Component {
//     constructor(props) {
//         super(props);
//         this.state = {  
//             listed:[],
//             error_status:" ",
//         }
//     }

//     async componentDidMount() {
      
//             await axios.get('http://localhost:8080/hackathons')
//             .then((response, error) => {
//                 this.setState({
//                     listed : this.state.listed.concat(response.data),
//                     error_status:" "
//                 })
//                 console.log("listed"+this.state.listed)
//             }).catch((error) => {
//                 console.log("Error",error)
//                 console.log("Error response",error.response.data)
//                 this.setState({error_status:error.response.data})
//             });

//     }

//     render() {         

//         return ( 
//         <div>
//             <div>               
//                 <nav class="navbar navbar-expand-lg navbar-light bg-light">
//                 <a class="navbar-brand" href="#">
//                     <img src={require('../images/2.png')} class="d-inline-block align-top" alt=""></img>
                    
//                 </a>
//                 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
//                     <span class="navbar-toggler-icon"></span>
//                 </button>

//                 <div class="collapse navbar-collapse" id="navbarSupportedContent">
//                     <ul class="navbar-nav mr-auto">
//                     <li class="nav-item active">
//                         <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
//                     </li>
//                     <li class="nav-item">
//                         <a class="nav-link" href="#">Link</a>
//                     </li>      
//                     <li class="nav-item">
//                         <a class="nav-link" href="#">Link</a>
//                     </li>
//                     <li class="nav-item">
//                         <a class="nav-link" href="#">Link</a>
//                     </li>             
//                     </ul>
//                     <form class="form-inline my-2 my-lg-0">
                    
//                     </form>
//                 </div>
//                 </nav>
//                 <div class="card">
//                 <Table id="hackathonTable">
//                     <TableHead>
//                     <TableRow>
//                         <TableCell>ID</TableCell>
//                         <TableCell align="right">Hackathon Name</TableCell>
//                         <TableCell align="right">Start Date</TableCell>
//                         <TableCell align="right">End Date</TableCell>
//                         <TableCell align="right">Fees</TableCell>
//                     </TableRow>
//                     </TableHead>
//                     <TableBody>
//                     {this.state.listed.map((row) => (
//                         <TableRow key={row.id}>
//                         <TableCell component="th" scope="row">
//                             {row.id}
//                         </TableCell>
//                         <TableCell align="right">{row.name}</TableCell>
//                         <TableCell align="right">{row.startDate}</TableCell>
//                         <TableCell align="right">{row.endDate}</TableCell>
//                         <TableCell align="right">{row.regFees}</TableCell>
//                         </TableRow>
//                     ))}
//                     </TableBody>
//                 </Table>
//                 </div>               
//             </div>            
//         </div> 
//         );
//     }
// }
 
// export default AdminDashboard;
