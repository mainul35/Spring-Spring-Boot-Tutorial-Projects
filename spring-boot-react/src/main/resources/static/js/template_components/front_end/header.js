import React from 'react';
import ReactDOM from 'react-dom';

export class FrontEndHeader extends React.Component {
    constructor(props){
    	super(props);
    }

    render(){
        return (
            <header>
                <h1>Header section</h1>
            </header>
        );
    }
}