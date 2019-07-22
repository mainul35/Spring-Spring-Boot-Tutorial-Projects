import * as React from 'react';

interface IEmployeeList {
    employees: any,
    isLoading: boolean
}

class EmployeeList extends React.Component<{}, IEmployeeList> {

    constructor(props: any) {
        super(props);

        this.state = {
            employees: [],
            isLoading: false
        }
    }

    public componentDidMount() {
        this.setState({isLoading: true});
        setInterval(()=>{
            // console.log("abc");
            fetch("http://localhost:8081/employees")
                .then(response => response.json())
                .then(data => {
                    this.setState({employees: data, isLoading: false})
                }).catch(error => {
                    console.log("Server offline or some error occured!")
            });
        }, 10000);

    }

    public addPersonScreen(event: any) {
        event.preventDefault();
        const firstName = document.getElementsByName('firstName')[0] as HTMLInputElement;
        const lastName = document.getElementsByName("lastName")[0] as HTMLInputElement;
        const description = document.getElementsByName("description")[0] as HTMLInputElement;

        console.log(firstName.value + " " + lastName.value + " " + description.value);

        fetch('http://localhost:8081/employees/add', {
            body: JSON.stringify({
                description: description.value,
                firstName: firstName.value,
                id: '',
                lastName: lastName.value
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            method: 'POST'
        })
    }


    public render() {

        if (this.state.isLoading) {
            return <p>Loading...</p>;
        }
        return (
            <div>
                <form>
                    <input type="text" name="firstName"/><br/>
                    <input type="text" name="lastName"/><br/>
                    <input type="text" name="description"/><br/>
                    <button onClick={this.addPersonScreen}>Add Person</button>
                </form>

                <h2>Person List</h2>
                {
                    // setInterval(function(){ alert("Hello"); }, 3000);
                    this.state.employees.map((employee: any) => {
                        return <div key={employee.id}>{employee.firstName + " " + employee.lastName}</div>;
                    })
                }
            </div>
        );
    }
}

export {EmployeeList};
