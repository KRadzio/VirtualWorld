#include "Turtle.h"

Turtle::Turtle(World &wR, const int &xPos, const int &yPos)
    : Animal(wR, xPos, yPos, SYMBOL, STRENGTH, INITIATIVE, AllOrganisms::TURTLE) { this->typeS = "Zolw"; }
Turtle::~Turtle() {}
bool Turtle::specialAbility(Organism *other)
{
    if (other->getStrength() < 5)
    {
        other->setPos(other->getPrevXPos(), other->getPrevYPos());
        string event = this->typeS + " przegania " + other->getTypeString();
        this->worldRef.addEvent(event);
        return true;
    }
    else
        return false;
}

void Turtle::takeAction()
{
    this->prevX = this->yPos;
    this->prevY = this->yPos;
    int newY = this->yPos;
    int newX = this->xPos;
    bool moveIsValid = false;
    while (moveIsValid == false)
    {
        int random = rand() % 4;
        switch (random)
        {
        case 0:
            if (this->worldRef.isPositionInsideWorld(newX, newY - this->moveRange))
            {
                newY--;
                moveIsValid = true;
            }
            break;
        case 1:
            if (this->worldRef.isPositionInsideWorld(newX, newY + this->moveRange))
            {
                newY++;
                moveIsValid = true;
            }
            break;
        case 2:
            if (this->worldRef.isPositionInsideWorld(newX + this->moveRange, newY))
            {
                newX++;
                moveIsValid = true;
            }
            break;
        case 3:
            if (this->worldRef.isPositionInsideWorld(newX - this->moveRange, newY)) // zwierzeta wychodza
            {
                newX--;
                moveIsValid = true;
            }
            break;
        }
    }

    if (this->worldRef.isPositionFree(newX, newY) != true)
        colision(this->worldRef.getOrganism(newX, newY));
    if (rand() % 4 == 0)
    {
        if (this->worldRef.isPositionFree(newX, newY) || this->worldRef.getOrganism(newX, newY)->checkIfIsAlive() == false)
            move(newX, newY);
    }
}