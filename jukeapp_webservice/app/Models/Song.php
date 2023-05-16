<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Song extends Model
{
    use HasFactory;

    public $table = "song";

    public $timestamps = false;

    protected $fillable = [
        'son_id',
        'son_img',
        'son_spotify_id',
        'son_artist1',
        'son_artist2',
        'son_name',
        'son_duration',
        'son_status'
    ];
}