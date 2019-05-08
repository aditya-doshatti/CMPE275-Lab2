import React, { Component } from 'react';
import axios from 'axios';
import '../css/hackathonPayment.css'

var swal = require('sweetalert')

const url="http://localhost:8080"

class HackathonPayment extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            userId:'',
            userOrg:'',
            hackId:this.props.location.state.hackId,
            hackathon:" ",
            sponsorList:[]
         }
    }

    async componentWillMount(){
        console.log("localStorage",localStorage.getItem('user'))
        const email=JSON.parse(localStorage.getItem('user'));
        axios.get(url+`/user/profile/${email}`)
        .then((response) => {
                this.setState({
                    userId:response.data.id,
                })
                if (response.data.organization!=null) {
                    this.setState({
                        userOrg:response.data.organization.name
                    })
                }
                console.log(response.data);
        });
        await axios.get(url+`/hackathon/${this.state.hackId}`)
        .then((response) =>{
            console.log("here inside axios",response.data)
            this.setState({
                hackathon: response.data,
                sponsorList: response.data.sponsors
            })
            console.log("aaa",response.data.sponsors);
        }
        );

        console.log("here inside",this.state.hackathon)
    }

    markPaymentDone = e => {
        axios.put(url+`/user/${this.state.userId}/pay`)
        .then((response)=>{
            console.log(response.data)
        });
        swal("Payment Done","Make your teammates pay","success")
        this.props.history.push('/dashboard')
    }

    handleCheck = val => {
        return this.state.sponsorList.some(item => val === item.name);
    }

    render() { 
        // if(this.state.hackathon!=null)
        //     a=this.state.hackathon.sponsors[0].id
        var sponsors = this.state.sponsorList.map((item, key) =>
        <span className="text-info font-weight-bold">{item.name}<br></br></span> 
        );
        var payment
        if (!this.handleCheck(this.state.userOrg)) {
            payment = <div>
                        <h5><b>Your Total: </b><span class="right-aligned">${this.state.hackathon.regFees}</span></h5> 
                    </div>
        }
        else {
            var amount = this.state.hackathon.regFees-(this.state.hackathon.regFees*(this.state.hackathon.discount*0.01))
            payment = <div>
                        <h5><b>Your Discounted Total: </b><span class="right-aligned">${amount}</span></h5>
                    </div>
        }
        return ( 
        <div>
            <div id = "paymentDiv">
                <h1>
                    Hackathon Payment Form
                </h1>
                <br></br>
                <div class="card">                    
                    <div class="container">
                        
                        <h4>Hackathon Details</h4>

                        <hr></hr>
                        <p>Hackathon Name:<span class="right-aligned">{this.state.hackathon.name}</span></p>
                        <p>Start Date:<span class="right-aligned"> {this.state.hackathon.startDate}</span></p>
                        <hr></hr>
                        <p>Sponsors:<span class="right-aligned">{sponsors}</span></p>
                        <p>Your organization:<span class="right-aligned">{this.state.userOrg}</span></p>
                        {payment}
                        {/* <h5><b>Your Total</b><span class="right-aligned">{this.state.hackathon.regFees}</span></h5>     */}

                        <hr></hr>     
                        <h4>Payment Method</h4>   
                        <br></br>              
                        <div id="paymentMethod">
                            <input type="radio" name="paytype" id="mastercard" />
                                <label for="mastercard"><img src={require("../images/mastercard.svg")} alt="Mastercard" class="payment-method-images" /></label>

                            <input type="radio" name="paytype" id="visa" />
                                <label for="happy"><img src={require("../images/visa3.jpg")} alt="Visa" class="payment-method-images" /></label>

                            <input type="radio" name="paytype" id="paypal" />
                                <label for="paypal"><img src={require("../images/paypal.png")} alt="PayPal" class="payment-method-images" /></label>
                                
                            <br></br>
                            <hr></hr>     
                            <button type="button" onClick={this.markPaymentDone} class="btn btn-dark">Make Payment</button>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
        );
    }
}
 
export default HackathonPayment;