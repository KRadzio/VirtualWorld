#pragma once
#include "Animal.h"

#define STRENGTH 3
#define INITIATIVE 7
#define SYMBOL 'L'

class Fox: public Animal
{
    public:
    Fox(World &wR, const int &xPos, const int &yPos);
    ~Fox();
    virtual void takeAction() override;
};