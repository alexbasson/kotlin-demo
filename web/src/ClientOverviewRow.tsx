import {default as React, ReactElement} from 'react';
import {Link} from 'react-router-dom';
import {ClientOverview} from './ClientOverview';
import {CurrencyFormatter} from './CurrencyFormatter';

interface Props {
  clientOverview: ClientOverview;
}

export const ClientOverviewRow = (props: Props): ReactElement => {
  const clientOverview = props.clientOverview;
  const client = clientOverview.client;

  return (
    <li className="link">
      <Link to={`/clients/${client.id}`}>
        <p className="align-left">{client.firstName} {client.lastName}</p>
        <p className="align-left">{client.email}</p>
        <p className="align-center">{clientOverview.numberOfAccounts}</p>
        <p className="align-right">{CurrencyFormatter.format(clientOverview.totalMarketValue)}</p>
      </Link>
    </li>
  )
};
