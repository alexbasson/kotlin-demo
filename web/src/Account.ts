export interface Account {
  id: string;
  number: string;
  fullname: string;
  nickname: string;
  type: string;
}

export interface CreateAccountRequest {
  clientId: string;
  number: string;
  fullname: string;
  nickname: string;
  type: string;
}
