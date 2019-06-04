import {Account} from './Account';

export interface GetAccountsForClientIdResultHandler {
  success(accounts: Account[]): void;
}

export interface AccountService {
  getAccountsForClientId(clientId: string, resultHandler: GetAccountsForClientIdResultHandler): void;
}
