import React, { Component } from 'react';
import axios from 'axios';

const url="http://localhost:8080"

class ApproveRequests extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            orgId:'',
            userId:'',
            pendingApprovals:[]
         }
    }

    handleApprove = (id) => {
        axios.put(url+`/organization/${this.state.orgId}/approve/${id}`)
        .then((response) => {
            console.log(response)
        });
    }

    handleReject = (id) => {

    }

    componentWillMount(){
        console.log("localStorage",localStorage.getItem('user'))
        const email=JSON.parse(localStorage.getItem('user'));
        axios.get(url+`/user/profile/${email}`)
        .then((response) => {
            this.setState({
                userId:response.data.id
            })
            axios.get(url+'/organizations/'+response.data.organization.id)
            .then((response)=> {
                this.setState({
                    orgId:response.data.id,
                    pendingApprovals:response.data.pendingApprovals
                })
                //console.log(response.data);
            });
        });
    }


    render() { 
        var items
        if(this.state.pendingApprovals!=null) {
        items = this.state.pendingApprovals.map((item, key) => <div>
        <span className="text-info font-weight-bold">{item.name}</span>
        <button onClick={()=>this.handleApprove(item.id)} className='btn-lg ml-3 col-lg-7 pull-right btn-link'>Approve</button>
        <button onClick={()=>this.handleReject(item.id)} className='btn-lg ml-3 col-lg-7 pull-right btn-link'>Reject</button>
        </div>
        );
        }
        return ( <div> <h1>Pending Approvals</h1>
                    {items}
                    </div>
                );
        }
}
 
export default ApproveRequests;