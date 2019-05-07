import React, {Component} from 'react';
import {Route} from 'react-router-dom';
import Login from './Login';
import Signup from './Signup';
import Profile from  './Profile';
import NotAuthorized from  './NotAuthorized';
import Hackathon from './Hackathon';
import CreateHackathon from './createHackathon';
import AdminDashboard from './AdminDashboard';
import HackerDashboard from './HackerDashboard';
//import Organization from './Organization';




class Main extends Component {
    render(){
        return(
            <div>
                {/*Render Different Component based on Route*/}

                {/* <Route exact path="/" component={Home} /> */}
                <Route exact path="/login" component={Login}/>
                <Route path="/signup" component={Signup}/>
                <Route path="/profile" component={Profile}/>
                <Route path="/notAuthorized" component={NotAuthorized}/>
                <Route path="/hackathon" component={Hackathon}/>
                <Route path="/create/hackathon" component={CreateHackathon} />
                <Route path="/admin/dashboard" component={AdminDashboard} />
                <Route path="/dashboard" component={HackerDashboard} />
                {/* <Route path="/organization" component={Organization}/> */}

            </div>
        )
    }
}
//Export The Main Component
export default Main;