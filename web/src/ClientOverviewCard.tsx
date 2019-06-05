import * as React from 'react';
import {ReactElement} from 'react';
import './Card.css';
import './ClientOverviewCard.css';
import {ClientService, GetClientOverviewsResultHandler} from './ClientService';
import {ClientOverviewRow} from './ClientOverviewRow';
import {ClientOverview} from './ClientOverview';

interface Props {
  clientService: ClientService;
}

interface State {
  clientOverviews: ClientOverview[];
}

export class ClientOverviewCard extends React.Component<Props, State> implements GetClientOverviewsResultHandler {

  constructor(props: Props) {
    super(props);
    this.state = {
      clientOverviews: []
    }
  }

  componentDidMount(): void {
    this.props.clientService.getClientOverviews(this);
  }

  render(): ReactElement {
    return (
      <div className="card client-overview-card">
        <h3>Clients</h3>
        <ul>
          <li className="border-bottom">
            <p className="strong align-left">Name</p>
            <p className="strong align-left">Email</p>
            <p className="strong align-center">Number of Accounts</p>
          </li>
          {this.state.clientOverviews.map((clientOverview) =>
            <ClientOverviewRow
              key={clientOverview.client.id}
              clientOverview={clientOverview}
            />
          )}
        </ul>
      </div>
    );
  }

  success(clients: ClientOverview[]) {
    this.setState({clientOverviews: clients});
  }

}
