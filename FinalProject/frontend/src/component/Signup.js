import React, { Component } from 'react';
import firebase from 'firebase';
import {NavLink} from 'react-router-dom';
import axios from 'axios';

const config = {
    apiKey: "AIzaSyCi3JYLWPtBMR6kxxRF-D8MisQVqEVu1DY",
    authDomain: "zhang-a2fe9.firebaseapp.com",
    databaseURL: "https://zhang-a2fe9.firebaseio.com",
    projectId: "zhang-a2fe9",
    storageBucket: "zhang-a2fe9.appspot.com",
    messagingSenderId: "122985196639"
  };

const fire=firebase.initializeApp(config);

class Signup extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            screenName:'',
            name:'',
            email:'',
            password: '',
            portraitUrl:'',
            businessTitle:'',
            aboutMe:'',
            address:'',
            error_message:" "
         }
         this.setField=this.setField.bind(this);
         this.submitEvent=this.submitEvent.bind(this);
         this.setScreenName=this.setScreenName.bind(this);
    }

    setField(e){
        var target=e.target;
        var value=target.value;
        var name=target.name;
        this.setState({
            [name]:value
        })
    }

    setScreenName(e){

        console.log("screenname",e.target.value)
        var target=e.target;
        var value=target.value;
        var name=target.name;
        this.setState({
            [name]:value
        })
        console.log("screenname",this.state.screenName)
        if(e.target.value.includes(" ")){
            this.setState({error_message:"Sceen Name can't contain space"})
        }else if(e.target.value.length<3){
            this.setState({error_message:"Sceen Name should be atleast 3 characters"})
        }else{
        this.setState({error_message:" "})
        axios.get(`http://localhost:8077/user/profile/sn/${e.target.value}`)
        .then((response) => {
            if(response.status==201){
              this.setState({error_message:" Screen Name is available"})
            }
            if(response.status==200){
                this.setState({error_message:"Not available"})
            }
         }).catch(function(error) {
            console.log("error occured",error)
            window.alert(error.code)
        });

        }

       
    }

    submitEvent(e){
        e.preventDefault();
        console.log("Inside signup form submission")
        var email=this.state.email
        var password=this.state.password
        console.log("submitin request",email,this.state.screenName)

        const data = {
            screenName:this.state.screenName,
            name:this.state.name,
            email : this.state.email,
            password : this.state.password,
            portraitUrl:this.state.portraitUrl,
            businessTitle:this.state.businessTitle,
            aboutMe:this.state.aboutMe,
            address:this.state.address
        }

        firebase.auth().createUserWithEmailAndPassword(email, password)
        .then((user) => {
            console.log("User(Register)",user)
                 window.alert("Register Successful")
            // this.props.history.push('/');
            var user = firebase.auth().currentUser;
            user.sendEmailVerification().then(function() {
                console.log("Email successfully sent")
                    axios.post('http://localhost:8077/user/signup', data)
                    .then((response) => {
                        console.log("Response received",response)
                     });
            }).catch(function(error) {
                console.log("error occured",error)
                window.alert(error.code)
            });

        })
        .catch((error) => {
            console.log("error in sign up",error.code);
            if(error.code=="auth/email-already-in-use")
                window.alert("Account already exists with this account!")
            else if(error.code=="auth/invalid-email")
                window.alert("Invalid Email format!")
            else if(error.code=="auth/weak-password")
                window.alert("Weak Password")
            else
                window.alert(error.code)
        });
    }

    render() { 
        return ( 
            <div className="text-center mt-5">
               <h1 class="title pt-2">Sign up for Hackathon</h1>
                Already have an account?<strong><NavLink to="/login"> Log in!</NavLink></strong>
               <form onSubmit={this.submitEvent}>
                <div>
                <input type="text" name="name" id="name" class="panel-input mt-2 w-50" placeholder="Full Name" onChange={this.setField} required/>
                </div>

                <div>
                <input type="text" name="screenName" id="screenName" class="panel-input mt-3 w-50" onChange={this.setScreenName} placeholder="Screen Name" required/>
                <h6 className="text-danger">{this.state.error_message}</h6>
                </div>

                <div>
                <input type="email" name="email" id="email" class="panel-input mt-3 w-50" onChange={this.setField} placeholder="Email address" required/>
                </div>
               
                <div>
               <input type="password" name="password" id="password" class="panel-input mt-3 w-50" onChange={this.setField}    placeholder="Password" required/>
                </div>

                  {/* <div>
               <input type="text" name="portraitUrl" id="portraitUrl" class="panel-input mt-3 w-50" onChange={this.setField}    placeholder="Portrait URL"/>
                </div>

                  <div>
               <input type="text" name="businessTitle" id="businessTitle" class="panel-input mt-3 w-50" onChange={this.setField}    placeholder="businessTitle"/>
                </div>

                  <div>
               <input type="text" name="aboutMe" id="aboutMe" class="panel-input mt-3 w-50" onChange={this.setField}    placeholder="aboutMe"/>
                </div>

                  <div>
               <input type="text" name="address" id="address" class="panel-input mt-3 w-50" onChange={this.setField}    placeholder="address"/>
                </div>  */}


                <button class="btn btn-submit text-white btn-large custom mt-4"><strong>Sign Me Up</strong></button> 
            
               
             </form>

            </div>
         );
    }
}
 
export default Signup;