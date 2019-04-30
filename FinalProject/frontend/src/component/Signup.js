import React, { Component } from 'react';
import firebase from 'firebase';
import {NavLink} from 'react-router-dom';

class Signup extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            email:'',
            password: ''
         }
         this.setField=this.setField.bind(this);
         this.submitEvent=this.submitEvent.bind(this);
    }

    setField(e){
        var target=e.target;
        var value=target.value;
        var name=target.name;
        this.setState({
            [name]:value
        })
    }

    submitEvent(e){
        e.preventDefault();
        console.log("Inside signup form submission")
        var email=this.state.email
        var password=this.state.password
        console.log(email)

        firebase.auth().createUserWithEmailAndPassword(email, password)
        .then((user) => {
            console.log("User(Register)",user)
                 window.alert("Register Successful")
            // this.props.history.push('/');
            var user = firebase.auth().currentUser;
            user.sendEmailVerification().then(function() {
                console.log("Email successfully sent")
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
                <input type="text" name="uname" id="uname" class="panel-input mt-2 w-50" placeholder="Full Name"/>
                </div>

                <div>
                <input type="email" name="email" id="email" class="panel-input mt-3 w-50" onChange={this.setField} placeholder="Email address"/>
                </div>
               
                <div>
               <input type="password" name="password" id="password" class="panel-input mt-3 w-50" onChange={this.setField}    placeholder="Password"/>
                </div> 
                <button class="btn btn-submit text-white btn-large custom mt-4"><strong>Sign Me Up</strong></button> 
            
               
             </form>

            </div>
         );
    }
}
 
export default Signup;