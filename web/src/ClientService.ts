import {Client} from "./Client";

export interface GetClientsResultHandler {
  success(clients: Client[]): void;
}

export interface GetClientResultHandler {
  success(client: Client): void;
}

export interface ClientService {
  getClients(resultHandler: GetClientsResultHandler): void;

  getClient(id: string, resultHandler: GetClientResultHandler): void;
}
