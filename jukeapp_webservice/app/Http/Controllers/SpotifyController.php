<?php

namespace App\Http\Controllers;
use Illuminate\Http\Request;
use DateTime;
class SpotifyController extends Controller
{
    public function getToken(){
        $getToken = 'curl -X POST "https://accounts.spotify.com/api/token" \ -H "Content-Type: application/x-www-form-urlencoded" \ -d "grant_type=client_credentials&client_id=5a6d8dfb099949bea1428cc7795f42b9&client_secret=389cff1d76a148408911968aef97df16"';

        $result = exec($getToken);

        $response = json_decode($result, true);

        $token = $response['access_token'];

        return $token;
    }

    public function searchSong($song){
        $command = 'curl --request GET \--url "https://api.spotify.com/v1/search?q=remaster%2520track%3ADoxy%2520artist%3AMiles%2520Davis&type=album" \
        --header "Authorization: Bearer undefined...undefined"';

        $result = exec($command);

        $response = json_decode($result, true);

        header('Content-Type: application/json');

        return response()->json($response);
    }

    public function adddSongQueue($id){

    }

    public function removeSongQueue($id){
        
    }

    public function getQueue(){

    }

    public function getHistory(){
        
    }
}
