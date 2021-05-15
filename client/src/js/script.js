import '../scss/style.scss';

import hello from '../modules/hello';
import auth from '../modules/req';
import req from '../modules/req';


window.addEventListener('DOMContentLoaded', () => {
    hello();
    req();
  });