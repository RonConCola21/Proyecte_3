<?php

namespace App\Http\Controllers;
use Illuminate\Http\Request;
use DateTime;
use App\Models\SpotifyToken;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Http;
use App\Models\Queue;
use App\Models\Song;
use App\Models\User;
use GuzzleHttp\Client;
use GuzzleHttp\Exception\GuzzleException;
class SpotifyController extends Controller
{
    public function checkToken()
    {
        $var_token = SpotifyToken::find(1);
        $refresh_token = null;
        if (!$var_token) {
            // Token is null, handle the case where there is no record in the table
            
        } else {
            $now = now(); // Current timestamp

            $lastTokenTimestamp = new DateTime($var_token->st_datetime); // Timestamp when the token was last updated
            $interval = $lastTokenTimestamp->diff($now);
            $minutesPassed = $interval->days * 24 * 60 + $interval->h * 60 + $interval->i;
            $refresh_token = $var_token->st_refresh_token;
            if ($minutesPassed > 60) {
                // More than an hour has passed since the last token update, handle token refresh
                $var_token->delete();
                $this->getToken2($refresh_token);
            }
        }
    }

    public function getAuthorization(){
        try {
            $client_id = '654bc854ce474703a7096dccb3ceb11d';
            $redirect_uri = 'http://localhost/jukeapp/public/api/getCode';
            $scopes = 'playlist-read-private playlist-modify-private playlist-modify-public playlist-read-collaborative user-read-playback-state user-modify-playback-state user-read-currently-playing user-read-recently-played';
            $authorizationUrl = 'https://accounts.spotify.com/authorize?' . http_build_query([
                'client_id' => $client_id,
                'response_type' => 'code',
                'redirect_uri' => $redirect_uri,
                'scope' => $scopes,
                'state' => '34fFs29kd09',
                'show_dialog' => 'true'
            ]);

            header('Location: ' . $authorizationUrl);
            die();

        } catch (\Illuminate\Database\QueryException $ex){
            return response()->json([
                'database_error' => "Database error: ".$ex
            ], 400);

        }catch(\Throwable $ex){
            return response()->json([
                'error' => "General error: ".$ex
            ], 400);
        }
    }

    public function getCode(Request $request){
        $request = $request->all();
        
        $code = $request['code'];

        $this->getToken($code);

    }

    public function getToken($code){
        $var_token = SpotifyToken::find(1);
        
        if ($var_token) {
            // Token exists, handle the case where there is already a record in the table
            $var_token->delete();
        }

        $data = new SpotifyToken();

        $client_secret = '81142fc9fc32451e96fb7835582ac1a2';

        $client_id = '654bc854ce474703a7096dccb3ceb11d';

        $grant_type = 'authorization_code';

        $redirect_uri = 'http://localhost/jukeapp/public/api/getCode';

        $params = [
            'client_secret' => $client_secret, // '81142fc9fc32451e96fb7835582ac1a2
            'client_id' => $client_id,
            'grant_type' => $grant_type,
            'code' => $code,
            'redirect_uri' => $redirect_uri,
            'code-verifier' => '1234567890123456789012345678901234567890123456789012345678901234'
        ];

        $response = Http::asForm()->post('https://accounts.spotify.com/api/token', $params);

        $result = $response;
        if ($result){
            $data->st_token = $result['access_token'];

            $datetime = new DateTime();

            $data->st_datetime = $datetime;

            $data->st_refresh_token = $result['refresh_token'];

            $data->save();
        }
        
    }

    public function returnTime(){
        $data = SpotifyToken::find(1);

        $datetime = new DateTime($data->st_datetime);

        $now = now();

        $interval = $datetime->diff($now);

        $minutesPassed = $interval->days * 24 * 60 + $interval->h * 60 + $interval->i;

        if ($data->st_refresh_token != 0){
            return 120 - $minutesPassed;
        } else {
            return 60 - $minutesPassed;
        }
    }

    public function getToken2($refresh_token){
        $var_token = SpotifyToken::find(1);
        
        if ($var_token) {
            // Token exists, handle the case where there is already a record in the table
            $var_token->delete();
        }

        $data = new SpotifyToken();

        $client = new Client();

        try {

            $response = $client->post('https://accounts.spotify.com/api/token',[
                'headers' => [
                    'Authorization' => 'Basic '.base64_encode('654bc854ce474703a7096dccb3ceb11d:81142fc9fc32451e96fb7835582ac1a2'),
                ],
                'form_params' => [
                    'grant_type' => 'refresh_token',
                    'refresh_token' => $refresh_token,
                ],
            ]);

            $statusCode = $response->getStatusCode();
            $responseData = $response->getBody()->getContents();

            $result = json_decode($responseData, true);

            $data = new SpotifyToken();
            $data->st_token = $result['access_token'];
            $datetime = new DateTime();
            $data->st_datetime = $datetime;
            $data->save();

        } catch (GuzzleException $e) {
            return response()->json([
                'status' => $e->getCode(),
                'data' => $e->getMessage()
            ], 400);
        }    
    }

    public function getToken3(){
        try{

            $data = new SpotifyToken();
            
            $getToken = 'curl -X POST "https://accounts.spotify.com/api/token" \ -H "Content-Type: application/x-www-form-urlencoded" \ -d "grant_type=client_credentials&client_id=5a6d8dfb099949bea1428cc7795f42b9&client_secret=389cff1d76a148408911968aef97df16"';

            $result = exec($getToken);

            $response = json_decode($result, true);

            $token = $response['access_token'];

            $datetime = new DateTime();

            $data->st_token = $token;

            $data->st_datetime = $datetime;

            $data->save();

        } catch (\Illuminate\Database\QueryException $ex){
            return response()->json([
                'database_error' => "Database error: ".$ex
            ], 400);
        }catch(\Throwable $ex){
            return response()->json([
                'error' => "General error: ".$ex
            ], 400);
        }
    }

    public function searchSong($song){

        $song = str_replace(' ', '%20', $song);

        $this->checkToken();

        $var_token = SpotifyToken::find(1);
        
        $response = Http::withHeaders([
            'Authorization' => 'Bearer '.$var_token->st_token,
        ])->get('https://api.spotify.com/v1/search?q='.$song.'&type=track&market=Es&limit=10');
        
        $result = $response->body();

        $result = json_decode($result, true);

        $tracks = $result['tracks'];

        $items = $tracks['items'];
        $list = array();
        foreach ($items as $item){
            $spotify_id = $item['id'];
            $dato = DB::table('song')->where('son_spotify_id', '=', $spotify_id)->first();
            if ($dato==null){
                $artists = $item['artists'];
                $artist = $artists[0];
                $artist_name1 = $artist['name'];
                if (count($artists) > 1){
                    $artist_name2 = $artists[1]['name'];
                }else{
                    $artist_name2 = "";
                }
                $id = 0;
                $images = $item['album']['images'];
                $son_img = $images[0]['url'];
                $son_album = $item['album']['name'];
                $duration = $item['duration_ms']/1000;
                $song_name = $item['name'];
                $search = 'p';
                $song = array(
                    'id' => $id,
                    'son_artist1' => $artist_name1,
                    'son_artist2' => $artist_name2,
                    'son_img' => $son_img,
                    'son_spotify_id' => $spotify_id,
                    'son_duration' => $duration,
                    'son_name' => $song_name,
                    'son_status' => $search,
                    'son_album' => $son_album
                );
                array_push($list, $song);
            }
            
        }
        
        return response()->json([
            'data' => $list
        ], 200);
    }

    public function addSongQueue(Request $request){
        $this->checkToken();

        $var_token = SpotifyToken::find(1);

        $song_id = $request->song_id;

        $user_id = $request->user_id;

        $response = Http::withHeaders([
            'Authorization' => 'Bearer '.$var_token->st_token,
        ])->post('https://api.spotify.com/v1/me/player/queue?uri=spotify:track:'.$song_id);

        $canco = Song::where('son_spotify_id','=', $song_id)->first();
        $queue = new Queue();
        $queue->que_users_id = $user_id;
        $queue->que_song_id = $canco->id;
        $queue->que_moment_temp = new DateTime();
        $queue->save();

        $user = User::find($user_id);
        $user->user_tokens = $user->user_tokens - 50;
        $user->save();

        return response()->json([
            'data' => $response->body()
        ], 200);
    }

    public function getQueue(){
        $this->checkToken();

        $var_token = SpotifyToken::find(1);

        $response = Http::withHeaders([
            'Authorization' => 'Bearer '.$var_token->st_token,
        ])->get('https://api.spotify.com/v1/me/player/queue');
        
        $result = $response->body();

        if ($result == null){
            return response()->json([
                'data' => null
            ], 200);
        }

        $result = json_decode($result, true);

        $queue = $result['queue'];

        $list = array();

        foreach ($queue as $item){
            $spotify_id = $item['id'];
            $artists = $item['artists'];
            $artist = $artists[0];
            $artist_name1 = $artist['name'];
            if (count($artists) > 1){
                $artist_name2 = $artists[1]['name'];
            }else{
                $artist_name2 = "";
            }
            $id = 0;
            $images = $item['album']['images'];
            $son_img = $images[0]['url'];
            $son_album = $item['album']['name'];
            $duration = $item['duration_ms']/1000;
            $song_name = $item['name'];
            $search = 'p';
            $song = array(
                'id' => $id,
                'son_artist1' => $artist_name1,
                'son_artist2' => $artist_name2,
                'son_img' => $son_img,
                'son_spotify_id' => $spotify_id,
                'son_duration' => $duration,
                'son_name' => $song_name,
                'son_status' => $search,
                'son_album' => $son_album
            );
            array_push($list, $song);
        }

        return response()->json([
            'data' => $list
        ], 200);
        
    }

    public function getHistory(){
        $this->checkToken();

        $var_token = SpotifyToken::find(1);

        $response = Http::withHeaders([
            'Authorization' => 'Bearer '.$var_token->st_token,
        ])->get('https://api.spotify.com/v1/me/player/recently-played?limit=10');

        $result = $response->body();

        $result = json_decode($result, true);

        $items = $result['items'];

        $list = array();
        
        foreach ($items as $item){
            $song = $item['track'];
            $artists = $song['artists'];
            $artist = $artists[0];
            $artist_name1 = $artist['name'];
            if (count($artists) > 1){
                $artist_name2 = $artists[1]['name'];
            }else{
                $artist_name2 = "";
            }
            $id = 0;
            $images = $song['album']['images'];
            $son_img = $images[0]['url'];
            $son_album = $song['album']['name'];
            $duration = $song['duration_ms']/1000;
            $song_name = $song['name'];
            $search = 's';
            $song = array(
                'id' => $id,
                'son_artist1' => $artist_name1,
                'son_artist2' => $artist_name2,
                'son_img' => $son_img,
                'son_duration' => $duration,
                'son_name' => $song_name,
                'son_status' => $search,
                'son_album' => $son_album
            );
            array_push($list, $song);
        }

        return response()->json([
            'data' => $list
        ], 200);
    }

    public function getCurrentlyPlayingSong(){
        $this->checkToken();

        $var_token = SpotifyToken::find(1);

        $response = Http::withHeaders([
            'Authorization' => 'Bearer '.$var_token->st_token,
        ])->get('https://api.spotify.com/v1/me/player/currently-playing');
        
        $result = $response->body();

        $result = json_decode($result, true);

        if ($result == null){
            return response()->json([
                'data' => null
            ], 200);
        }

        $item = $result['item'];

        $artists = $item['artists'];
        $artist = $artists[0];
        $artist_name1 = $artist['name'];
        if (count($artists) > 1){
            $artist_name2 = $artists[1]['name'];
        }else{
            $artist_name2 = "";
        }
        $id = 0;
        $images = $item['album']['images'];
        $son_img = $images[0]['url'];
        $son_album = $item['album']['name'];
        $duration = $item['duration_ms']/1000;
        $song_name = $item['name'];
        $search = 's';
        $song = array(
            'id' => $id,
            'son_artist1' => $artist_name1,
            'son_artist2' => $artist_name2,
            'son_img' => $son_img,
            'son_duration' => $duration,
            'son_name' => $song_name,
            'son_status' => $search,
            'son_album' => $son_album
        );
            
        return response()->json([
            'data' => $song
        ], 200);

    }

    public function getDevice(){
        $this->checkToken();

        $var_token = SpotifyToken::find(1);

        $response = Http::withHeaders([
            'Authorization' => 'Bearer '.$var_token->st_token,
        ])->get('https://api.spotify.com/v1/me/player/devices');

        $result = $response->body();

        $result = json_decode($result, true);

        $devices = $result['devices'][0]['id'];

        return $devices;
    }

    public function getPlaylistItems(){
        $this->checkToken();

        $var_token = SpotifyToken::find(1);

        $response = Http::withHeaders([
            'Authorization' => 'Bearer '.$var_token->st_token,
        ])->get('https://api.spotify.com/v1/playlists/1VQ1joMy8snbNPlndNI2iU/tracks');

        $result = $response->body();

        $result = json_decode($result, true);

        $items = $result['items'];

        $list = array();

        foreach ($items as $item){
            $song = $item['track'];
            $artists = $song['artists'];
            $artist = $artists[0];
            $artist_name1 = $artist['name'];
            if (count($artists) > 1){
                $artist_name2 = $artists[1]['name'];
            }else{
                $artist_name2 = "";
            }
            $id = 0;
            $spotify_id = $song['id'];
            $images = $song['album']['images'];
            $son_img = $images[0]['url'];
            $son_album = $song['album']['name'];
            $duration = $song['duration_ms']/1000;
            $song_name = $song['name'];
            $search = 's';
            $song = array(
                'id' => $id,
                'son_artist1' => $artist_name1,
                'son_artist2' => $artist_name2,
                'son_img' => $son_img,
                'son_duration' => $duration,
                'son_name' => $song_name,
                'son_status' => $search,
                'son_album' => $son_album,
                'son_spotify_id' => $spotify_id
            );
            $var = Song::where('son_spotify_id', $spotify_id)->first();
            if ($var == null){
                $var = new Song();
                $var->son_name = $song_name;
                $var->son_artist1 = $artist_name1;
                $var->son_artist2 = $artist_name2;
                $var->son_img = $son_img;
                $var->son_duration = $duration;
                $var->son_album = $son_album;
                $var->son_status = 'w';
                $var->son_spotify_id = $spotify_id;
                $var->save();
            }
            array_push($list, $song);
        }

        return response()->json([
            'data' => $list
        ], 200);
    }

    public function updatePlaylistItems(){
        $this->checkToken();

        $var_token = SpotifyToken::find(1);

        $response = Http::withHeaders([
            'Authorization' => 'Bearer '.$var_token->st_token,
        ])->put();
    }

    public function skipNext(){
        $this->checkToken();

        $var_token = SpotifyToken::find(1);

        $response = Http::withHeaders([
            'Authorization' => 'Bearer '.$var_token->st_token,
        ])->post('https://api.spotify.com/v1/me/player/next');

        return response()->json([
            'success' => 'ok'
        ], 200);
    }

    public function skipPrevious(){
        $this->checkToken();

        $var_token = SpotifyToken::find(1);

        $response = Http::withHeaders([
            'Authorization' => 'Bearer '.$var_token->st_token,
        ])->post('https://api.spotify.com/v1/me/player/previous');

        return response()->json([
            'success' => 'ok'
        ], 200);
    }

    public function pause(){

        $this->checkToken();

        $var_token = SpotifyToken::find(1);

        $device = $this->getDevice();

        $response = Http::withHeaders([
            'Authorization' => 'Bearer '.$var_token->st_token,
        ])->put('https://api.spotify.com/v1/me/player/pause');

        $var_token->st_device = $device;
        $var_token->save();

        return response()->json([
            'success' => 'ok'
        ], 200);
    }

    public function resume(){
        $this->checkToken();

        $var_token = SpotifyToken::find(1);

        $device = $var_token->st_device;

        if ($device){
            $response = Http::withHeaders([
                'Authorization' => 'Bearer '.$var_token->st_token,
            ])->put('https://api.spotify.com/v1/me/player/play?device_id='.$device);
        }

        return response()->json([
            'success' => 'ok'
        ], 200);
    }
}
