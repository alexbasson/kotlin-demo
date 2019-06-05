import {Client} from './Client';
import {ClientOverview} from './ClientOverview';

export interface GetClientOverviewsResultHandler {
  success(clientOverviews: ClientOverview[]): void;
}

export interface GetClientResultHandler {
  success(client: Client): void;
}

export interface ClientService {
  getClientOverviews(resultHandler: GetClientOverviewsResultHandler): void;

  getClient(id: string, resultHandler: GetClientResultHandler): void;
}
