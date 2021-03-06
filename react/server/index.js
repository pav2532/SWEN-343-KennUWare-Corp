/* eslint consistent-return:0 */

const express = require('express');
const logger = require('./logger');
const proxy = require('express-http-proxy');

const argv = require('minimist')(process.argv.slice(2));
const setup = require('./middlewares/frontendMiddleware');
const resolve = require('path').resolve;
const app = express();

// If you need a backend, e.g. an API, add your custom backend-specific middleware here
// This is to be able to hit the Java Spark server with /api
app.use('/api/sales', proxy('localhost:8000'));
app.use('/api/customer-support', proxy('localhost:8001'));
app.use('/api/sso', proxy('localhost:8003'));

// In production we need to pass these values in instead of relying on webpack
setup(app, {
  outputPath: resolve(process.cwd(), 'build'),
  publicPath: '/',
});

// get the intended port number, use port 3000 if not provided
const port = argv.port || process.env.PORT || 3000;

// Start your app.
app.listen(port, (err) => {
  if (err) {
    return logger.error(err.message);
  }
});
