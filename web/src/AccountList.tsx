import * as React from 'react';
import {Account} from './Account';
import {AccountService, GetAccountsForClientIdResultHandler} from './AccountService';
import {ReactElement} from 'react';
import {AccountListRow} from "./AccountListRow";

interface Props {
  clientId: string;
  accountService: AccountService;
}

interface State {
  accounts: Account[];
}

export class AccountList extends React.Component<Props, State> implements GetAccountsForClientIdResultHandler {

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
      <ul>
        {
          this.state.accounts.map((account) => <AccountListRow key={account.id} account={account}/>)
        }
      </ul>
    )
  }

}
