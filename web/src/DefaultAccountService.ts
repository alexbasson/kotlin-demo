import {AccountService, CreateAccountResultHandler, GetAccountsForClientIdResultHandler} from './AccountService';
import {CreateAccountRequest} from './Account';

export class DefaultAccountService implements AccountService {

  private baseUrl = 'http://localhost:8080/api';

  getAccountsForClientId(clientId: string, resultHandler: GetAccountsForClientIdResultHandler): void {
    fetch(this.baseUrl + `/clients/${clientId}/accounts`)
      .then(response => response.json())
      .then(accounts => resultHandler.success(accounts))
      .catch(error => console.log(error));
  }

  createAccountForClientId(clientId: string, request: CreateAccountRequest, resultHandler: CreateAccountResultHandler): void {
    fetch(this.baseUrl + '/accounts', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(request)
    })
      .then(response => response.json())
      .then(account => resultHandler.accountCreated(account))
      .catch(error => console.error(error));
  }

}
