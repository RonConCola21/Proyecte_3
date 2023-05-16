<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Petition extends Model
{
    use HasFactory;

    public $table = "petition";

    public $timestamps = false;

    protected $fillable = [
            'id',
            'pet_song_id'
    ];
}
