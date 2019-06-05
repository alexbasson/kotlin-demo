import * as React from 'react';
import {ReactElement} from 'react';
import './Card.css';
import './AccountsCard.css';
import {Account} from './Account';
import {AccountService, CreateAccountResultHandler, GetAccountsForClientIdResultHandler} from './AccountService';
import {AccountsCardRow} from './AccountsCardRow';
import {CreateAccountForm} from './CreateAccountForm';

interface Props {
  clientId: string;
  accountService: AccountService;
}

interface State {
  accounts: Account[];
  displayForm: boolean;
}

export class AccountsCard
  extends React.Component<Props, State>
  implements GetAccountsForClientIdResultHandler, CreateAccountResultHandler {

  constructor(props: Props) {
    super(props);
    this.state = {
      accounts: [],
      displayForm: false
    };

    this.toggleDisplayForm = this.toggleDisplayForm.bind(this);
  }

  componentDidMount(): void {
    this.fetchAccounts();
  }

  success(accounts: Account[]) {
    this.setState({accounts});
  }

  render(): ReactElement {
    return (
      <div className="card accounts-card">
        <div className="card-header fdr fjb">
          <h3>Accounts</h3>
          <button onClick={this.toggleDisplayForm}>+</button>
        </div>
        <table cellPadding="0" cellSpacing="0">
          <thead>
          <tr>
            <th className="align-left">Account Number</th>
            <th className="align-left">Account Type</th>
            <th className="align-left">Full name</th>
            <th className="align-right">Market Value</th>
          </tr>
          </thead>
          <tbody>
          {
            this.state.accounts.map((account) => <AccountsCardRow key={account.id} account={account}/>)
          }
          </tbody>
        </table>

        {
          this.state.displayForm ?
            <CreateAccountForm
              accountService={this.props.accountService}
              clientId={this.props.clientId}
              createAccountRequestHandler={this}
            />
            :
            ''
        }
      </div>
    )
  }

  toggleDisplayForm() {
    this.setState({displayForm: !this.state.displayForm});
  }

  accountCreated(account: Account): void {
    this.setState({displayForm: false});
    this.fetchAccounts();
  }

  private fetchAccounts() {
    const clientId = this.props.clientId;
    this.props.accountService.getAccountsForClientId(clientId, this);
  }

}
