export interface Client {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
}

export interface CreateClientRequest {
  firstName: string;
  lastName: string;
  email: string;
}
