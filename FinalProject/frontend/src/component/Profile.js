import React, { Component } from 'react';
import axios from 'axios';

class Profile extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            email:JSON.parse(localStorage.getItem('user')),
            screenName:'',
            aboutMe:'',
            address:'',
            businessTitle:'',
            portraitUrl:'',
            id:0
         }
         this.handleEvent=this.handleEvent.bind(this);
         this.submitEvent=this.submitEvent.bind(this)
    }

    handleEvent(e){
        let target=e.target
        let name=target.name;
        this.setState({
            [name]:target.value    
        });
        console.log("handle Ecent for update profile")
    }

    submitEvent(e){
        e.preventDefault();

        const id=this.state.id

        const data=({
            email:this.state.email,
            name:this.state.name,
            aboutMe:this.state.aboutMe,
            address:this.state.address,
            businessTitle:this.state.businessTitle,
            portraitUrl:this.state.portraitUrl
        })
        
        axios.put(`http://localhost:8077/user/profile/${this.state.id}`,data)
        .then((response) => {
                this.setState({
                    name:response.data.name,
                    aboutMe:response.data.aboutMe,
                    address:response.data.address,
                    businessTitle:response.data.businessTitle,
                    portraitUrl:response.data.portraitUrl
                })
                console.log(response.data);
        });
    }


    componentWillMount(){
        console.log("localStorage",localStorage.getItem('user'))
        const email=JSON.parse(localStorage.getItem('user'));
        axios.get(`http://localhost:8077/user/profile/${email}`)
        .then((response) => {
                this.setState({
                    id:response.data.id,
                    screenName:response.data.screenName,
                    name:response.data.name,
                    aboutMe:response.data.aboutMe,
                    address:response.data.address,
                    businessTitle:response.data.businessTitle,
                    portraitUrl:response.data.portraitUrl
                })
                console.log(response.data);
        });
    }
    render() { 
        return ( <div>
                   <div className="container-fluid">
            <div className="row">
                <div className=" col-lg-7 mb-5  mt-5 ml-5 bg-white border border-light" >
                    <form onSubmit={this.submitEvent}>

                        <h3 className="text-left mt-4 ">Profile Information</h3>
                        <hr></hr>   
                        <div className="mt-4 mr-5"   >
                        <span className="text-info font-weight-bold">Screen Name:</span>
                            <input type="text" className=" ml-4 btn-lg col-lg-7 pull-right"  value={this.state.screenName} disabled/>
                        </div><br></br>

                        <div className="mt-4 mr-5" >
                           <span className="text-info font-weight-bold">Email:</span>
                            <input type="text" className=" btn-lg col-lg-7 pull-right" value={this.state.email} disabled />
                        </div>

                        <div className="mt-4 mr-5"   >
                        <span className="text-info font-weight-bold">Full Name:</span>
                            <input type="text" className=" ml-4 btn-lg col-lg-7 pull-right" name="name" id="name" onChange={this.handleEvent} placeholder={this.state.name}/>
                        </div><br></br>
                        
                        <div className="mt-5 mr-5">
                            <span className="text-info font-weight-bold">About Me:</span>
                            <textarea rows="6" cols="40" className="mt-4 ml-5 btn-lg pull-right" onChange={this.handleEvent} placeholder={this.state.aboutMe} name="aboutMe" id="aboutMe"   max="500"/>
                        </div><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br>

                        <div className="mt-4 mr-5"  >
                         <span className="text-info font-weight-bold">Business Title:</span>
                            <input type="text" className="btn-lg ml-5 col-lg-7 pull-right" onChange={this.handleEvent} placeholder={this.state.business_title} name="business_title" defaultValue={this.state.business_title} id="business_title"  />
                        </div><br></br>

                        <div className="mt-4 mr-5"  >
                         <span className="text-info font-weight-bold">Portrait URL:</span>
                            <input type="text" className="btn-lg ml-4 col-lg-7 pull-right" onChange={this.handleEvent} placeholder={this.state.portraitUrl} name="portraitUrl" id="portraitUrl" defaultValue={this.state.portraitUrl}  />
                        </div><br></br>
                        
                        <div className="mt-4 mr-5 mb-4"  >
                         <span className="text-info font-weight-bold">Address</span>
                            <input type="text" className="btn-lg ml-3 col-lg-7 pull-right" onChange={this.handleEvent} placeholder={this.state.address} name="address" id="address" defaultValue={this.state.address} />
                        </div><br></br>
                    
                    <div className="row text-center mt-4 ml-5">
                         <button type="submit" className="mt-4 mb-4 ml-5 btn btn-submit bg-primary text-white btn-lg ">Save Changes</button>
                    </div>
                    </form>
                </div>
               
                {/* <div className="col-lg-3 text-left mt-5 border-left" >
                </div> */}

            </div>
        </div>
        </div> );
    }
}
 
export default Profile;