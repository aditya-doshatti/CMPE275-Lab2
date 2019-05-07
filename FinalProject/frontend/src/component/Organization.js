import React, { Component } from 'react';
import axios from 'axios';

class Organization extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            name:'',
            owner:'',
            description:'',
            address:''
         }

         this.submitEvent=this.submitEvent.bind(this)
    }

    submitEvent(e) {
        e.preventDefault();
        const data=({

        })
    }

    render() { 
        return ( 
            <div>
                <div className="container-fluid">
                    <div className="row">
                    <div className=" col-lg-7 mb-5  mt-5 ml-5 bg-white border border-light">
                    <form onSubmit={this.submitEvent}>
                        <h3 className="text-left mt-4 ">Organization Information</h3>
                        <hr></hr>
                        <div className="mt-4 mr-5">
                        <span className="text-info font-weight-bold">Organization Name:</span>
                            <input type="text" className=" ml-4 btn-lg col-lg-7 pull-right"  value={this.state.screenName} disabled/>
                        </div>
                    </form>
                    </div>
                    </div>
                </div>                
            </div>
         );
    }
}
 
export default Organization;