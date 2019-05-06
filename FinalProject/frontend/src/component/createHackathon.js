import React, { Component } from 'react';
import "../css/createHackathon.css"

class CreateHackathon extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
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
            <form>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="hackathonNameLabel">
                    <label for="inputHackathonName">Hackathon Name</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="text" class="form-control hackInputs" id="inputHackathonName" placeholder="Hackathon Name" ></input>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="startTimeLabel">
                    <label for="inputStartTime">Hackathon Name</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="date" class="form-control hackInputs" id="inputStartTime" placeholder="Time"></input>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="endTimeLabel">
                    <label for="inputendTime">Hackathon Name</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="date" class="form-control hackInputs" id="inputendTime" placeholder="Time"></input>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="descriptionLabel">
                    <label for="inputDescription">Hackathon Name</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <textarea class="form-control" id="inputDescription" rows="3"></textarea>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="feeLabel">
                    <label for="inputfee">Hackathon Name</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="number" class="form-control hack-inputs" id="inputfee" placeholder="participation fee"></input>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="judgeOneLabel">
                    <label for="inputJudgeOne">First Judge</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="email" class="form-control hack-inputs" id="inputJudgeOne" placeholder="email id of judge"></input>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="judgeTwoLabel">
                    <label for="inputJudgeTwo">Second Judge</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="email" class="form-control hack-inputs" id="inputJudgeTwo" placeholder="email id of judge"></input>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="maxSizeLabel">
                    <label for="inputMaxSize">Maximum team size</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="number" class="form-control hack-inputs" id="inputMaxSize" placeholder="max"></input>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="minSizeLabel">
                    <label for="inputMinSize">Minimum team size</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="number" class="form-control hack-inputs" id="inputMinSize" placeholder="min"></input>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="sponsorLabel">
                    <label for="inputSponsor">Sponsor Organization</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="text" class="form-control hack-inputs" id="inputSponsor" placeholder="Sponsor"></input>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 input-labels" id="sponsorDiscountLabel">
                    <label for="inputSponsorDiscount">Sponsor Discount</label>                
                    </div>
                    <div class="form-group col-md-9">
                    <input type="text" class="form-control hack-inputs" id="inputSponsorDiscount" placeholder="Discount"></input>
                    </div>
                </div>
            </form>
            
        </div> 
        );
    }
}
 
export default CreateHackathon;