import {AccountService, GetAccountsForClientIdResultHandler} from "./AccountService";

export class DefaultAccountService implements AccountService {

  private baseUrl = 'http://localhost:8080/api';

  getAccountsForClientId(clientId: string, resultHandler: GetAccountsForClientIdResultHandler): void {
    fetch(this.baseUrl + `/clients/${clientId}/accounts`)
      .then(response => response.json())
      .then(accounts => resultHandler.success(accounts))
      .catch(error => console.log(error));
  }

}
