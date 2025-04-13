#pragma once
#include "Animal.h"

#define STRENGTH 4
#define INITIATIVE 4
#define SYMBOL 'O'

class Sheep: public Animal
{
    public:
    Sheep(World &wR, const int &xPos, const int &yPos);
    ~Sheep();
};