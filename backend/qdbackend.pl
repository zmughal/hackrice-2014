#!/usr/bin/env perl
use Mojolicious::Lite;

# Documentation browser under "/perldoc"
plugin 'PODRenderer';

get '/api/fuel/:lat/:lng' => sub {
  my $self = shift;
  $self->render(json => [
	{ name => 'Shell', lat => '29.7169', lon => '-95.4028' },
	{ name => 'Exxon', lat => '29.718922', lon => '-95.339162' },
  ]);
};

app->start;
__DATA__

@@ index.html.ep
% layout 'default';
% title 'Welcome';
Welcome to the Mojolicious real-time web framework!

@@ layouts/default.html.ep
<!DOCTYPE html>
<html>
  <head><title><%= title %></title></head>
  <body><%= content %></body>
</html>
