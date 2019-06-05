import * as React from 'react';
import './Card.css';
import './AccountsCard.css';
import {Account} from './Account';
import {AccountService, GetAccountsForClientIdResultHandler} from './AccountService';
import {ReactElement} from 'react';
import {AccountsCardRow} from './AccountsCardRow';

interface Props {
  clientId: string;
  accountService: AccountService;
}

interface State {
  accounts: Account[];
}

export class AccountsCard extends React.Component<Props, State> implements GetAccountsForClientIdResultHandler {

  constructor(props: Props) {
    super(props);
    this.state = {
      accounts: []
    }
  }

  componentDidMount(): void {
    const clientId = this.props.clientId;
    this.props.accountService.getAccountsForClientId(clientId, this);
  }

  success(accounts: Account[]) {
    this.setState({accounts});
  }

  render(): ReactElement {
    return (
      <div className="card accounts-card">
        <h3>Accounts</h3>
        <table cellPadding="0" cellSpacing="0">
          <thead>
          <tr>
            <th>Account Number</th>
            <th>Account Type</th>
            <th>Full name</th>
          </tr>
          </thead>
          <tbody>
          {
            this.state.accounts.map((account) => <AccountsCardRow key={account.id} account={account}/>)
          }
          </tbody>
        </table>
      </div>
    )
  }

}
