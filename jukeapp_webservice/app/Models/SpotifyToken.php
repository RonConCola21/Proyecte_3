<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class SpotifyToken extends Model
{
    use HasFactory;

    public $table = "spotify_token";

    public $timestamps = false;

    protected $fillable = [
        'id',
        'st_token',
        'st_datetime',
        'st_device',
        'st_refresh-token'
    ];
}

