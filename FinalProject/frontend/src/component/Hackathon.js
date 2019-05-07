import React, { Component } from 'react';

import "../css/card.css"

class Hackathon extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    onUploadClick(e) {
        let files = e.target.files;
        console.log("Data files", files);
    }
    render() { 
        return ( 
            <div>
                
                <div class="row">
                    
                    <div class="col-md-9">
                        <div class="card" >
                            <div class="card-body">
                                <h5 class="card-title"><b>Problem Description</b></h5>
                                <h6 class="card-subtitle mb-2 text-muted">2 Sum Problem</h6>
                                <p class="card-text">Given an array of integers, return indices of the two numbers such that they add up to a specific target.</p>
                                <p class="card-text">You may assume that each input would have exactly one solution, and you may not use the same element twice.</p>
                                <hr></hr>
                                <samp>
                                    <h6><b>Example</b></h6>
                                    <h6>Input</h6>
                                    nums = [2, 7, 11, 15], target = 9 <br></br><br></br>
                                    <h6>Output</h6>
                                    [0, 1]

                                </samp>
                                <hr></hr>
                                {/* <a href="#" class="card-link">Upload Solution</a> */}
                                <input type="file" name="file"  onChange={(e)=>this.onUploadClick(e)}></input>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">      
                        <div class="table-container">
                            <table class="table table-dark">                        
                            <tbody>
                                <tr>                            
                                <td>Author</td>
                                <td>Anand</td>                                
                                </tr>
                                <tr>                                
                                <td>Difficulty</td>
                                <td>Easy</td>                                
                                </tr>
                                <tr>                                
                                <td>Max Points</td>
                                <td>50</td>                                
                                </tr>
                            </tbody>
                            </table>
                        </div>            
                    </div>
                </div>
            </div>
         );
    }
}
 
export default Hackathon;