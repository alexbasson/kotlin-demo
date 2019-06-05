import {ClientService, GetClientResultHandler, GetClientOverviewsResultHandler} from './ClientService';

export class DefaultClientService implements ClientService {

  private baseUrl = 'http://localhost:8080/api';

  getClientOverviews(resultHandler: GetClientOverviewsResultHandler): void {
    fetch(this.baseUrl + '/clients')
      .then(response => response.json())
      .then(clientOverviews => resultHandler.success(clientOverviews))
      .catch(error => console.log(error));
  }

  getClient(id: string, resultHandler: GetClientResultHandler): void {
    fetch(this.baseUrl + `/clients/${id}`)
      .then(response => response.json())
      .then(client => resultHandler.success(client))
      .catch(error => console.log(error));
  }

}
