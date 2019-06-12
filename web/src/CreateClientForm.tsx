import * as React from 'react';
import {ChangeEvent, FormEvent, ReactElement} from 'react';
import './Forms.css';
import {CreateClientRequest} from './Client';
import {ClientService, CreateClientResultHandler} from './ClientService';

interface Props {
  clientService: ClientService;
  createClientResultHandler: CreateClientResultHandler;
}

interface State {
  firstName: string;
  lastName: string;
  email: string;
}

export class CreateClientForm extends React.Component<Props, State> {

  constructor(props: Props) {
    super(props);
    this.state = {
      firstName: '',
      lastName: '',
      email: ''
    };

    this.submit = this.submit.bind(this);
    this.updateFirstName = this.updateFirstName.bind(this);
    this.updateLastName = this.updateLastName.bind(this);
    this.updateEmail = this.updateEmail.bind(this);
  }

  render(): ReactElement {
    return (
      <form onSubmit={this.submit}>
        <h4>New Client</h4>
        <div className="input-group">
          <label htmlFor="firstName">First name: </label>
          <input
            type="text"
            name="firstName"
            value={this.state.firstName}
            onChange={this.updateFirstName}
          />
        </div>

        <div className="input-group">
          <label htmlFor="lastName">Last name: </label>
          <input
            type="text"
            name="lastName"
            value={this.state.lastName}
            onChange={this.updateLastName}
          />
        </div>

        <div className="input-group">
          <label htmlFor="emailName">Email: </label>
          <input
            type="text"
            name="email"
            value={this.state.email}
            onChange={this.updateEmail}
          />
        </div>

        <div className="fdr fjc">
          <button className="btn" type="submit">Create new client</button>
        </div>
      </form>
    )
  }

  updateFirstName(event: ChangeEvent<HTMLInputElement>) {
    const firstName = event.target.value;
    this.setState({firstName});
  }

  updateLastName(event: ChangeEvent<HTMLInputElement>) {
    const lastName = event.target.value;
    this.setState({lastName});
  }

  updateEmail(event: ChangeEvent<HTMLInputElement>) {
    const email = event.target.value;
    this.setState({email});
  }

  submit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    this.props.clientService.createClient(
      this.buildCreateClientRequest(),
      this.props.createClientResultHandler
    );
  }

  private buildCreateClientRequest(): CreateClientRequest {
    return {
      firstName: this.state.firstName,
      lastName: this.state.lastName,
      email: this.state.email
    };
  }
}
