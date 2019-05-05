import React, { Component } from 'react';
import firebase from 'firebase';
import {NavLink} from 'react-router-dom';
// import fire from '../config/fire'


class Login extends Component {
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
        var headers = new Headers();
        console.log("I am here inside login submit event")
        e.preventDefault();
        var email=this.state.email
        var password=this.state.password

        //firebase authentication
        firebase.auth().signInWithEmailAndPassword(email, password)
            .then((user) => {
                  console.log("User(Login)",user)
                  if(firebase.auth().currentUser.emailVerified==false){
                    this.props.history.push('/notAuthorized')
                  } else{
                     window.localStorage.setItem('user', JSON.stringify(email));
                     this.props.history.push('/profile');
                  }
            })
            .catch((error) => {
                  console.log("error code(Login)",error.code)
                  if(error.code=="auth/user-not-found")
                    window.alert("No account exists with given email")
                  else if(error.code="auth/wrong-password")
                    window.alert("Wrong/Invalid password")
                  else
                    window.alert(error.code)
            });
    }

    render() { 
        return ( 
            <div className="text-center mt-5">
              <form onSubmit={this.submitEvent}>
               <h1 class="title pt-2">Login to Hackathon</h1>
                    Need an account?<strong><NavLink to="/signup"> Sign Up!</NavLink></strong>
              
                <div>
                
                <input type="email" name="email" id="email" class="panel-input mt-3 w-25" onChange={this.setField} placeholder="Email address" required />
                </div>
               
                <div>
               <input type="password" name="password" id="password" class="panel-input mt-3 w-25" onChange={this.setField}   placeholder="Password" required />
                </div> 
                <button class="btn btn-submit text-white btn-large mt-4 custom" onSubmit={this.submitEvent}><strong>Login</strong></button> 
                </form>
            </div>
         );
    }
}
 
export default Login;