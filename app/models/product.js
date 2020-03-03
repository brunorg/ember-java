import classic from 'ember-classic-decorator';
import Model, { attr } from '@ember-data/model';

@classic
export default class Product extends Model {
  @attr()
  name;

  @attr()
  description;

  @attr()
  price;
}
