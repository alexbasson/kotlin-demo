import {AccountService, CreateAccountResultHandler} from './AccountService';
import * as React from 'react';
import {FormEvent} from 'react';
import {ChangeEvent} from 'react';
import {CreateAccountRequest} from './Account';

interface Props {
  accountService: AccountService;
  clientId: string;
  createAccountRequestHandler: CreateAccountResultHandler;
}

interface State {
  accountNumber: string;
  fullName: string;
  nickname: string;
  type: string;
}

export class CreateAccountForm extends React.Component<Props, State> {

  constructor(props: Props) {
    super(props);
    this.state = {
      accountNumber: '',
      fullName: '',
      nickname: '',
      type: 'CHECKING'
    };

    this.updateAccountNumber = this.updateAccountNumber.bind(this);
    this.updateFullName = this.updateFullName.bind(this);
    this.updateNickname = this.updateNickname.bind(this);
    this.updateType = this.updateType.bind(this);
    this.submit = this.submit.bind(this);
  }

  render() {
    return (
      <form onSubmit={this.submit}>
        <h4>New Account</h4>
        <div className="input-group">
          <label htmlFor="accountNumber">Account number: </label>
          <input
            type="text"
            name="accountNumber"
            value={this.state.accountNumber}
            onChange={this.updateAccountNumber}
          />
        </div>

        <div className="input-group">
          <label htmlFor="fullName">Full name: </label>
          <input
            type="text"
            name="fullName"
            value={this.state.fullName}
            onChange={this.updateFullName}
          />
        </div>

        <div className="input-group">
          <label htmlFor="nickname">Nickname: </label>
          <input
            type="text"
            name="nickname"
            value={this.state.nickname}
            onChange={this.updateNickname}
          />
        </div>

        <div className="input-group">
          <label htmlFor="nickname">Type: </label>
          <select
            value={this.state.type}
            onChange={this.updateType}
          >
            <option value="CHECKING">Checking</option>
            <option value="SAVINGS">Savings</option>
            <option value="INVESTMENT">Investment</option>
            <option value="CREDIT">Credit</option>
          </select>
        </div>

        <div className="fdr fjc">
          <button className="btn" type="submit">Create new account</button>
        </div>
      </form>
    );
  }

  updateAccountNumber(event: ChangeEvent<HTMLInputElement>) {
    const accountNumber = event.target.value;
    this.setState({accountNumber});
  }

  updateFullName(event: ChangeEvent<HTMLInputElement>) {
    const fullName = event.target.value;
    this.setState({fullName});
  }

  updateNickname(event: ChangeEvent<HTMLInputElement>) {
    const nickname = event.target.value;
    this.setState({nickname});
  }

  updateType(event: ChangeEvent<HTMLSelectElement>) {
    const type = event.target.value;
    this.setState({type});
  }

  submit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    this.props.accountService.createAccountForClientId(
      this.props.clientId,
      this.buildCreateAccountRequest(),
      this.props.createAccountRequestHandler
    );
  }

  private buildCreateAccountRequest(): CreateAccountRequest {
    return {
      clientId: this.props.clientId,
      number: this.state.accountNumber,
      fullname: this.state.fullName,
      nickname: this.state.nickname,
      type: this.state.type
    };
  }

}
