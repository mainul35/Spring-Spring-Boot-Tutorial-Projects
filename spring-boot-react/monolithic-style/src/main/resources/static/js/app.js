const React = require('react');
const ReactDOM = require('react-dom');
const $ = require('jquery');
const axios = require('axios');
import {FrontEndHeader} from './template_components/front_end/header';
import '../styles/main.scss';

class Employee extends React.Component {
	render() {
		return (
			<tr>
				<td>{this.props.employee.firstName}</td>
				<td>{this.props.employee.lastName}</td>
				<td>{this.props.employee.description}</td>
			</tr>
		);
	}
}

class EmployeeList extends React.Component {
	constructor(props) {
		super(props);
	}
	
	render() {
		let employees = this.props.employees.map((employee)=>{
			console.log(employee);
			return (
					<Employee key={employee.id} employee={employee} />
			);
		});
				
		return (
			<table>
				<tbody>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Description</th>
					</tr>
					{employees}
				</tbody>
			</table>
		);
	}
}

class App extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			employees: [],
		};
		this.componentDidMount = this.componentDidMount.bind(this);
	}

	componentDidMount() {
		axios.get('/employees').then(res => {
			this.setState(() => {
				return {
					employees: res.data,
				};
			});
		});
	}

	render() {
		return <EmployeeList employees={this.state.employees} />;
	}
}

ReactDOM.render(<App />, document.getElementById('react'));
