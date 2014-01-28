#!/usr/bin/env perl
use Mojolicious::Lite;

get '/api/fuel/:lat/:lng' => sub {
  my $self = shift;
  $self->render(json => [
	{ name => 'Shell', lat => '29.7169', lon => '-95.4028', price => '$2.73' },
	{ name => 'Exxon', lat => '29.718922', lon => '-95.339162', price => '$2.75' },
  ]);
};

any '/api/image' => sub {
	my $self = shift;
	use DDP; p $self;
	if( my $file = $self->param('file') ) {
		my $filename = $file->filename;
		use DDP; p $filename;
		$ENV{PATH} = "$ENV{HOME}/sw_projects/hackrice-2014/ssocr-2.16.0";
		my $return = system( 'ssocr' => qw(-d -1) => $filename);
		$self->render( text => $return );
	} else {
		$self->render( text => '14.327' );
	}
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
