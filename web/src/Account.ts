export interface Account {
  id: string;
  number: string;
  fullname: string;
  nickname: string;
  type: string;
  marketValue: number;
}

export interface CreateAccountRequest {
  clientId: string;
  number: string;
  fullname: string;
  nickname: string;
  type: string;
}
