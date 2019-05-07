import React, { Component } from 'react';
import axios from 'axios';
import "../css/createHackathon.css"

const url="http://localhost:8080"
class CreateHackathon extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            inputHackathonName:'',
            inputStartTime:'',
            inputendTime:'',
            inputDescription:'',
            inputfee:0,
            inputJudgeOne:'',
            inputJudgeTwo:'',
            inputMaxSize:0,
            inputMinSize:0,
            inputSponsor:'',
            inputSponsorDiscount:0
         }
         this.setField=this.setField.bind(this);
         this.submitForm=this.submitForm.bind(this);
    }

    setField(e){
        let target=e.target
        let name=target.name;
        this.setState({
            [name]:target.value    
        });
        console.log(target.value)
        console.log("set field for hackathon event")
    }

    submitForm(e){
        e.preventDefault();

        const id=this.state.id

        const data=({
            "name": this.state.inputHackathonName,
            "description": this.state.inputDescription,
            "startDate": this.state.inputStartTime,
            "endDate": this.state.inputendTime,
            "regFees": this.state.inputfee,
            "isOpen":0,
            "minTeamSize":this.state.inputMaxSize,
            "maxTeamSize": this.state.inputMinSize,
        })
        
        axios.post(url+'/hackathon',data)
        .then((response) => {
                // this.setState({
                //     name:response.data.name,
                //     aboutMe:response.data.aboutMe,
                //     address:response.data.address,
                //     businessTitle:response.data.businessTitle,
                //     portraitUrl:response.data.portraitUrl
                // })
                console.log(response);
        });
    }

    render() { 
        return ( 
        <div>
            <div class="row">
                <div class="col-md-12">
                    <h1 id="H1_1">
                        Create Contest
                    </h1>
                </div>
            </div>
            <br></br>
            <br></br>
            <form onSubmit={this.submitForm}>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="hackathonNameLabel">
                    <label for="inputHackathonName">Hackathon Name</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="text" class="form-control hackInputs" name="inputHackathonName" id="inputHackathonName" placeholder="Hackathon Name"
                    onChange={this.setField} ></input>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="startTimeLabel">
                    <label for="inputStartTime">Hackathon Name</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="date" class="form-control hackInputs" id="inputStartTime" name="inputStartTime" placeholder="Time" onChange={this.setField}></input>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="endTimeLabel">
                    <label for="inputendTime">Hackathon Name</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="date" class="form-control hackInputs" id="inputendTime" name="inputendTime" placeholder="Time" onChange={this.setField}></input>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="descriptionLabel">
                    <label for="inputDescription">Hackathon Name</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <textarea class="form-control" id="inputDescription" name="inputDescription" rows="3" onChange={this.setField}></textarea>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="feeLabel">
                    <label for="inputfee">Participation Fee</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="number" class="form-control hack-inputs" id="inputfee" name="inputfee" placeholder="participation fee" onChange={this.setField}></input>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="judgeOneLabel">
                    <label for="inputJudgeOne">First Judge</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="email" class="form-control hack-inputs" id="inputJudgeOne" name="inputJudgeOne" placeholder="email id of judge" onChange={this.setField}></input>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="judgeTwoLabel">
                    <label for="inputJudgeTwo">Second Judge</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="email" class="form-control hack-inputs" id="inputJudgeTwo" name="inputJudgeTwo" placeholder="email id of judge" onChange={this.setField}></input>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="maxSizeLabel">
                    <label for="inputMaxSize">Maximum team size</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="number" class="form-control hack-inputs" id="inputMaxSize" name="inputMaxSize" placeholder="max" onChange={this.setField}></input>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="minSizeLabel">
                    <label for="inputMinSize">Minimum team size</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="number" class="form-control hack-inputs" id="inputMinSize" name="inputMinSize" placeholder="min" onChange={this.setField}></input>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="sponsorLabel">
                    <label for="inputSponsor">Sponsor Organization</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <select class="form-control" id="inputSponsor" name="inputSponsor" multiple>
                        <option>Google</option>
                        <option>Facebook</option>
                        <option>Atlassian</option>
                        <option>LinkedIn</option>
                        <option>Github</option>
                    </select>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="sponsorDiscountLabel">
                    <label for="inputSponsorDiscount">Sponsor Discount</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="text" class="form-control hack-inputs" id="inputSponsorDiscount" name="inputSponsorDiscount" placeholder="Discount"></input>
                    </div>
                </div>

                <button className="btn btn-primary">Create Hackathon</button>
            </form>
            
        </div> 
        );
    }
}
 
export default CreateHackathon;