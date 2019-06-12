import {Account, CreateAccountRequest} from './Account';

export interface GetAccountsForClientIdResultHandler {
  success(accounts: Account[]): void;
}

export interface CreateAccountResultHandler {
  accountCreated(account: Account): void;
}

export interface AccountService {
  getAccountsForClientId(clientId: string, resultHandler: GetAccountsForClientIdResultHandler): void;
  createAccountForClientId(clientId: string, request: CreateAccountRequest, resultHandler: CreateAccountResultHandler): void;
}
